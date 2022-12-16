const router = require('express').Router()
const imgModel = require('../models/imgmodel')

router.post('/update_validity', (req, res) => {
    var imgid = req.body.imgid
	console.log(imgid)
	var bool_ = (req.body.bool == undefined)? false : req.body.bool
	imgModel.findOneAndUpdate({'imageName': imgid}, {$set: {'rightYn': bool_}}
		).then(response => {
			console.log('111')
			console.log(response)
		}).catch(err => {
			console.log('222')
			console.log(err)

		})
		res.send({'aaa': imgid, 'bbb': imgModel})

})

router.get('/aaaa', (req, res) => res.send({'test': 'Hello World'}))

router.post('/remove', (req, res) => {
    var imgid = req.body.imgid
	console.log(imgid)
	var bool_ = false
	imgModel.findOneAndRemove({'imageName': imgid})
		.then(response => {
			console.log('111')
			console.log(response)
		}).catch(err => {
			console.log('222')
			console.log(err)

		})
		res.send({'aaa': imgid, 'bbb': imgModel})
})

module.exports = router