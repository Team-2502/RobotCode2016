
def processData(found, processedImage, center, box, angle) :
	keys = ["hot", "cx", "cy", "bw", "bh", "bx", "by", "angle"]
	values = [found, center[0], center[1], box[1][0], box[1][1], box[0][0], box[0][1], angle]
	return keys, values

