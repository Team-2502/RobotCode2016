import cv2
import numpy as np
import time


ifDebug = False
ifDisplay = True

def setDebug(bug) :
    ifDebug = bug

def displayImage(img, comment=None) :
    useImg = img
    if ifDisplay :
        # if comment != None :
        #     font = cv2.FONT_HERSHEY_SIMPLEX
        #     cv2.putText(useImg, comment, (10,400), font, 4,(255,255,255),2,cv2.LINE_AA)
        cv2.imshow("Frame", useImg)
        if (ifDebug) :
            cv2.waitKey(0)

def displayModImage(img) :
    cv2.imshow("Frame 2", img)
    if (ifDebug) :
        cv2.waitKey(0)

def drawColorContours(contours, img) :
    colImg = img
    # colImg = cv2.cvtColor(useImg, cv2.COLOR_GRAY2BGR)
    colImg = cv2.drawContours(colImg, contours, -1, (0,255,0), 3)
    return colImg

# def drawModColorContours(contours, img) :
#     colImg = img
#     # colImg = cv2.cvtColor(useImg, cv2.COLOR_GRAY2BGR)
#     colImg = cv2.drawContours(colImg, contours, -1, (255,100,0), 3)
#     return colImg

def polyContours(contours) :
    modContours = []
    for cnt in contours :
        epsilon = 0.008*cv2.arcLength(cnt,True)
        approx = cv2.approxPolyDP(cnt,epsilon,True)
        modContours.append(approx)
    return modContours

def getTargetBox(contours) :
    boxes = []
    angles = []
    rects = []
    for cnt in contours :
        rect = cv2.minAreaRect(cnt)
        box = cv2.boxPoints(rect)
        box = np.int0(box)
        boxes.append(box)
        angles.append(rect[2])
        rects.append(rect)
    return (boxes, angles, rects)

def dilate(img) :
    dilImg = cv2.dilate(img, cv2.getStructuringElement(cv2.MORPH_RECT,(5,5)))
    return dilImg

def drawBoxOfImage(boxes, img) :
    im = img
    # im = cv2.cvtColor(im, cv2.COLOR_GRAY2BGR)
    im = cv2.drawContours(im,boxes,-1,(0,0,255),2)
    return im

def drawCircleAt(img, x, y) :
    centerImg = cv2.circle(img, (int(x), int(y)), int(6), (255, 100, 0), 2)
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
    origImage = cv2.flip(origImage, 0)
    hsvImg = cv2.cvtColor(origImage, cv2.COLOR_BGR2HSV)
    # start_time = timeSinceLast("Col Convert", start_time)

    displayImage(hsvImg, "HSV")


    greenRanged = greenRange(hsvImg)
    # start_time = timeSinceLast("Green", start_time)

    # cannyEdges = cv2.Canny(greenRanged, 100, 200)
    displayImage(greenRanged, "Green")

    dilated = dilate(greenRanged)
    displayImage(dilated)
    # ret3, threshedToWhite = cv2.threshold(greenRanged,0,255,cv2.THRESH_BINARY+cv2.THRESH_OTSU)
    # displayImage(threshedToWhite)
    contourImage, contours, hierarchy = cv2.findContours(dilated,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
    # start_time = timeSinceLast("Contours", start_time)

    contours = polyContours(contours)
    # start_time = timeSinceLast("Poly Contours", start_time)

    # print contours
    # processContour = contours[0]

    # origContours = contours
    # otherImage = origImage
    colContImg = drawColorContours(contours, origImage)
    displayImage(colContImg, "Col Contours")

    
    # cnt = betterContours[0]
    # hull = cv2.convexHull(cnt,returnPoints = False)
    # defects = cv2.convexityDefects(cnt,hull)

    # for i in range(defects.shape[0]):
    #     s,e,f,d = defects[i,0]
    #     start = tuple(cnt[s][0])
    #     end = tuple(cnt[e][0])
    #     far = tuple(cnt[f][0])
    #     cv2.line(otherImage,start,end,[0,255,0],2)
    #     cv2.circle(otherImage,far,5,[0,0,255],-1)
    #     otherImage = drawModColorContours(betterContours, otherImage)
    
    # displayModImage(otherImage)
    # start_time = timeSinceLast("Before box", start_time)

    boxes, angles, rects = getTargetBox(contours)
    # start_time = timeSinceLast("Target Box", start_time)

    # boxedImg = drawBoxOfImage(boxes, colContImg)
    # displayImage(boxedImg)


    cx = None
    cy = None
    boxedImg = colContImg
    # boxedImg2 = betterColContImg
    boxFound = False
    # writeFile = False
    
     for (box, angle, rect, cnt) in zip(boxes, angles, rects, contours) :
    # if box != None :
        # start_time = timeSinceLast("before area", start_time)

        area = cv2.contourArea(cnt)
        # start_time = timeSinceLast("contour Area", start_time)

        rectArea = rect[1][0] * rect[1][1]

        # print "Normal Contours"

        # print "Contour area: ",
        # print area

        # print "Rect Area: ",
        # print rectArea

        # print "Better Contours"
        # area = cv2.contourArea(betterContours[i])
        # rectArea = rects[i][1][0] * rects[i][1][1]

        # print "Contour area: ",
        # print area

        # print "Rect Area: ",
        # print rectArea

        if rectArea > 500 and (float(area) / rectArea) < .6 :

            isConvex = cv2.isContourConvex(cnt)

            if not isConvex :
                print cnt
                if len(cnt) == 8 or True :
                    hull = cv2.convexHull(cnt, returnPoints=True)
                    pointNum = len(hull)
                    # print "Normal Contours"
                    # print "Points: ",
                    # print pointNum
                    # print hull
                    # for c in hull :
                    #     drawCircleAt(boxedImg, c[0][1], c[0][1])

                    # hull2 = cv2.convexHull(betterContours[i], returnPoints=True)
                    # pointNum2 = len(hull2)
                    # print "Better Contours"
                    # print "Points: ",
                    # print pointNum2
                    # print hull2

                    # for c1 in hull2 :
                    #     drawCircleAt(boxedImg2, c1[0][1], c1[0][1])

                    M = cv2.moments(cnt)
                    cx = int(M["m10"] / M["m00"])
                    cy = int(M["m01"] / M["m00"])

                    boxedImg = drawBoxOfImage([box], boxedImg)
                    boxedmg = drawCircleAt(boxedImg, cx, cy)
                    displayImage(boxedImg, "Box Image")
                    # setDebug(True)
                    # displayModImage(boxedImg2)

                    boxFound = True
                    # if rectArea > 20000 :
                    #     writeFile = True
    # displayImage(boxedImg)
    if boxFound :
        imgToReturn = boxedImg
    else :
        imgToReturn = origImage
    timeSinceLast("End", start_time)
    return boxFound, imgToReturn, (cx, cy), boxes, angles