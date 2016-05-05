from picamera.array import PiRGBArray
from picamera import PiCamera
import cv2
import numpy as np

def setupVideoFile(fileName) :
	vidcap = cv2.VideoCapture(fileName)
	success,image = vidcap.read()
	return success, image, vidcap
	
def getVideoFileFrame(vidcap) :
		success, origImage = vidcap.read()
		return success, origImage

def getImageFile(fileName) :
	img = cv2.imread(fileName)
	return img

def setupPiCamera() :
	# initialize the camera and grab a reference to the raw camera capture
	camera = PiCamera()
	camera.resolution = (640, 480)
	camera.framerate = 32
	time.sleep(2)

	#Set the values for the camera
	camera.shutter_speed = 400
	# camera.shutter_speed = camera.exposure_speed

	camera.exposure_mode = 'off'
	g = camera.awb_gains
	camera.awb_mode = 'off'
	camera.awb_gains = g
	camera.iso = 100

	rawCapture = PiRGBArray(camera, size=(640, 480))
	 
	# allow the camera to warmup
	time.sleep(0.1)
	return (camera, rawCapture)
