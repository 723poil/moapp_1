const router = require('express').Router()
const imgModel = require('../models/imgmodel')

router.post('/update-validity', (req, res) => {
    var imgid = req.body.imgid
	
	var bool_ = (req.body.bool == undefined)? false : req.body.bool
	imgModel.findOneAndUpdate({'imageName': imgid}, {$set: {'rightYn': bool_}}).then(response => {
		res.send({
			'result': true
		})
	}).catch(err => {
		res.send({
			'result': false
		})

	})
})

router.post('/remove', (req, res) => {
    var imgid = req.body.imgid
	console.log(imgid)
	var bool_ = false
	imgModel.findOneAndRemove({'imageName': imgid}).then(response => {
		res.send({
			'result': true
		})
	}).catch(err => {
		res.send({
			'result': false
		})

	})
})

module.exports = router