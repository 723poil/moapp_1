const mongoose = require('mongoose');
const collectionName = 'img'

const imgModelSchema = new mongoose.Schema({
    
	imageName: { type: String, required: true },
	dateTime: { type: Date, default: Date.now },
	classification: { type: String },
	rightYn: {type: Boolean, default: true},
	useYn: {type: Boolean, default: false}
},
{
    collection: collectionName,
    versionKey: false
});


imgModelSchema.statics.findAll = function() {

    return this.find({})
}

module.exports = mongoose.model(collectionName, imgModelSchema)