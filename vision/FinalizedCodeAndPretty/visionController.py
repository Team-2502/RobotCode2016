#!/home/pi/.virtualenvs/cv/bin/python

import cv2
import visionTools as vt
import cameraProcessing as cp
import communicationTools as coms
import processData as pd
from picamera.array import PiRGBArray
from picamera import PiCamera


def processImage(origImage) :

    found, processedImage, center, boxes, angles = vt.processImage(origImage)
    # imageKeys, imageValues = pd.processData(found, processedImage, center, boxes, angles)
    # coms.sendData(imageKeys, imageValues)
    # vt.displayImage(processedImage, origImage, "Done")

    return processedImage

def imageFileProcess(fileName) :
    img = processImage(cv2.imread("../testpicam/videoFramesToUse/%s.jpg" % fileName, 1))
    # cv2.imwrite("../testpicam/videoFramesToUse/Pro%s.jpg" % fileName, img)
    cv2.waitKey(0)

def loopCameraProcess() :
    camera, rawCapture = cp.setupPiCamera()

    # capture frames from the camera
    for frame in camera.capture_continuous(rawCapture, format="bgr", use_video_port=True):
        # grab the raw NumPy array representing the image, then initialize the timestamp
        # and occupied/unoccupied text
        
        image = frame.array
        processImage(image)
        
        # cv2.imshow("Frame", recognizedImage)
        # key = cv2.waitKey(1) & 0xFF
     
        # clear the stream in preparation for the next frame
        rawCapture.truncate(0)
     
        # if the `q` key was pressed, break from the loop
        if cv2.waitKey(1) & 0xFF == ord("q") :
            break

def loopVideoProcess() :
    success, image, vidcap = cp.setupVideoFile("../testpicam/matchStuff.h264")

    while success :
        success, image = cp.getVideoFileFrame(vidcap)
        processImage(image)
        if cv2.waitKey(1) & 0xFF == ord("q") :
            break

imageFileProcess("img1")
imageFileProcess("doubleGoal")
for i in range(33) :
    imageFileProcess("testImg%d" % i)
loopVideoProcess()
