import picamera
import time
# import RPi.GPIO as GPIO

# GPIO.setmode(GPIO.BOARD)

# GPIO.setup(23, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)

# while GPIO.input(23) ==1:


with picamera.PiCamera() as camera:
	camera.framerate = 32
	camera.resolution = (640, 480)
	time.sleep(2)
	camera.shutter_speed = 400


	camera.exposure_mode = 'off'
	g = camera.awb_gains
	camera.awb_mode = 'off'
	camera.awb_gains = g
	camera.iso = 100

	time.sleep(.1)


	camera.start_recording('matchStuff.h264')
	camera.wait_recording(360)
	camera.stop_recording()
