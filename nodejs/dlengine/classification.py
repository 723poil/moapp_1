import tensorflow as tf

# import tensorflow as tf
import numpy as np
import sys

model = tf.keras.models.load_model('./dlengine/model.h5')

classes = ['Aluminium', 'Carton', 'Glass', 'Organic Waste', 'Other Plastics', 'Paper and Cardboard', 'Plastic', 'Textiles', 'Wood']

path = './images/' + sys.argv[1]

img = tf.keras.preprocessing.image.load_img(path, target_size=(256, 256))
img_array = tf.keras.preprocessing.image.img_to_array(img)
img_array = tf.expand_dims(img_array, 0) 

predictions = model.predict(img_array)

print('result:'+classes[np.argmax(predictions)], end='')