import cv2
import numpy as np
import time


ifDebug = False
ifDisplay = True
imNum = 0

def setDebug(bug) :
    ifDebug = bug

def displayImage(img, origImg, comment=None) :
    useImg = img
    if ifDisplay :
        cv2.imshow("Processed", useImg)
        cv2.imshow("Original", origImg)
        if (ifDebug) :
            cv2.waitKey(0)
def saveImage(img) :
    cv2.imwrite("../testpicam/videoFramesToUse/imProNum%d.jpg" % imNum, img)
    global imNum
    imNum += 1

def drawColorContours(contours, img) :
    colImg = img
    colImg = cv2.drawContours(colImg, contours, -1, (0,255,0), 3)
    return colImg

def drawHullContours(hull, img) :
    colImg = img
    colImg = cv2.drawContours(colImg, hull, -1, (0, 255, 255), 3)
    for p in hull[0] :
        # print (p[0][0], p[0][1])
        colImg = cv2.circle(colImg, (p[0][0], p[0][1]), 5,[0, 255, 255],-1)
    return colImg

def normalContours(img) :
    useImg = img.copy()
    image, contours, hierarchy = cv2.findContours(useImg,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
    return img, contours, hierarchy

def polyContours(contours, eps=.01) :
    modContours = []
    for cnt in contours :
        arcLength = cv2.arcLength(cnt,True)
        epsilon = eps*arcLength
        approx = cv2.approxPolyDP(cnt,epsilon,True)
        modContours.append(approx)

    return modContours

def getTargetBox(contours) :
    boxes = []
    angles = []
    rects = []
    for cnt in contours :
        rect = cv2.minAreaRect(cnt)
        area = rect[1][0] * rect[1][1]
        if area > 2700 :
            print "Rect Area"
            print area

            box = cv2.boxPoints(rect)
            box = np.int0(box)
            boxes.append(box)
            angles.append(rect[2])
            rects.append(rect)
    return (boxes, angles, rects)

def removeNoise(img) :
    kernel = np.ones((5,5),np.uint8)
    dilImg = cv2.dilate(img, kernel)
    erosion = cv2.erode(dilImg,kernel,iterations = 1)
    return erosion

def drawBoxOfImage(boxes, img) :
    im = img
    # im = cv2.cvtColor(im, cv2.COLOR_GRAY2BGR)
    im = cv2.drawContours(im,boxes,-1,(0,0,255),2)
    return im

def drawCircleAt(img, x, y) :
    useImg = img
    centerImg = cv2.circle(useImg, (int(x), int(y)), int(6), (255, 100, 0), 2)
    return centerImg

def greenRange(img) :
    im = img
    minGreenArr = [50, 190, 20]
    maxGreenArr = [75, 255, 200]

    minGreen = np.array(minGreenArr)
    maxGreen = np.array(maxGreenArr)

    greenRanged = cv2.inRange(im, minGreen, maxGreen)
    return greenRanged

def timeSinceLast(label, start_time) :
    print(label + " --- %s seconds ---" % (time.time() - start_time))
    return start_time
def timeOverall() :
    pass

def processImage(origImage) :
    start_time = time.time()
    print "Flip"
    origImage = cv2.flip(origImage, 0)
    print "HSVCol"
    hsvImg = cv2.cvtColor(origImage, cv2.COLOR_BGR2HSV)

    print "Green ranged"
    greenRanged = greenRange(hsvImg)
    
    print "Dilated"
    dilated = removeNoise(greenRanged)
    # displayImage(dilated, origImage)
    
    contourImage, contours, hierarchy = normalContours(dilated)
    contours = polyContours(contours)
    
    print "Contours"
    dilated = cv2.cvtColor(dilated, cv2.COLOR_GRAY2BGR)
    colContImg = drawColorContours(contours, dilated)
    # displayImage(colContImg, origImage, "Col Contours")

    boxes, angles, rects = getTargetBox(contours)

    cx = None
    cy = None
    boxedImg = colContImg
    boxFound = False
    
    for (box, angle, rect, cnt) in zip(boxes, angles, rects, contours) :

        area = cv2.contourArea(cnt)
        rectArea = rect[1][0] * rect[1][1]

        if rectArea > 500 and (float(area) / rectArea) < .6 :

            isConvex = cv2.isContourConvex(cnt)

            if not isConvex :
                print "Contour Length: %d" % len(cnt)
                if len(cnt) == 8 or True :

                    hullDefectUse = cv2.convexHull(cnt, returnPoints=False)
                    defects = cv2.convexityDefects(cnt, hullDefectUse)
                    actualHulls = 0
                    if defects == None :
                        continue
                    for i in range(defects.shape[0]):
                        s,e,f,d = defects[i,0]
                        if d < 20000 and d > 1000 :
                            actualHulls += 1

                    if actualHulls == 1 :
                        hull = cv2.convexHull(cnt, returnPoints=True)
                        pointNum = len(hull)
                        print "Hull Length: %d" % pointNum

                        boxHull = polyContours([hull], eps=.1)

                        boxedImg = drawHullContours(boxHull, boxedImg)  

                        if len(boxHull[0]) == 4 :

                            for i in range(defects.shape[0]):
                                s,e,f,d = defects[i,0]
                                start = tuple(cnt[s][0])
                                end = tuple(cnt[e][0])
                                if d < 20000 and d > 1000 :
                                    boxedImg = cv2.line(boxedImg, start, end,[255, 0, 255], 2)
                                    boxedImg = cv2.circle(boxedImg, start,5,[255, 0, 255],-1)
                                    boxedImg = cv2.circle(boxedImg, end,5,[255, 0, 255],-1)

                            # displayImage(boxedImg, origImage, "hull")

                            M = cv2.moments(cnt)
                            cx = int(M["m10"] / M["m00"])
                            cy = int(M["m01"] / M["m00"])

                            boxedImg = drawCircleAt(boxedImg, cx, cy)
                            # displayImage(boxedImg, origImage, "Box Image")
                            boxFound = True
                            
    # displayImage(boxedImg)
    if boxFound :
        imgToReturn = boxedImg
        # saveImage(origImage)
    else :
        imgToReturn = origImage
    # timeSinceLast("End", start_time)
    displayImage(imgToReturn, origImage, "Box Image")
    return boxFound, imgToReturn, (cx, cy), boxes, angles