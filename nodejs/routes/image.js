const router = require('express').Router()
const crypto = require('crypto')
const multer = require('multer')
const spawn = require('child_process').spawn

const storage = multer.diskStorage({
    destination: 'images/',
    filename: (req, file, cb) => {
        return crypto.pseudoRandomBytes(16, (err, raw) => {
            if(err) {
                return cb(err)
            }
            return cb(null, file.originalname)
        })
    }
})

const upload = multer({ storage: storage})

router.post('/classification', upload.single('file'), (req, res) => {
    try {
        let file = req.file
        let originalname = ''
        let fileName = ''
        let mimeType = ''
        let size = 0
        let result

        if (file) {
            originalname = file.originalname
            fileName = file.filename
            mimeType = file.mimeType
            size = file.size

            result = spawn('python', ['../dlengine/classification.py', fileName])
        }

        console.log('originalname : ' + originalname + 
                ', \nfileName : ' + fileName + 
                ', \nmimeType : ' + mimeType + 
                ', \nsize : ' + size)
        
        result.stdout.on('data', (data) => {
            console.log(fileName + ' result : ' + data.toString())
            res.send({
                'originalname' : originalname,
                'fileName' : fileName,
                'mimeType' : mimeType,
                'size' : size,
                'status' : true,
                'result' : data.toString()
            })
        })

        result.stderr.on('data', (data) => {
            console.log(fileName + ' result : ' + data.toString())
            res.send({
                'originalname' : originalname,
                'fileName' : fileName,
                'mimeType' : mimeType,
                'size' : size,
                'status' : true,
                'result' : 'no result'
            })
        })
    } catch (err) {
        console.log(err)
        res.send({
            'originalname' : '',
            'fileName' : '',
            'mimeType' : '',
            'size' : 0,
            'status' : false,
            'result' : 'no result'
        })
    }
})

router.post('/', upload.single('file'), (req, res) => {
    try {
        let file = req.file
        let originalname = ''
        let fileName = ''
        let mimeType = ''
        let size = 0

        if (file) {
            originalname = file.originalname
            fileName = file.filename
            mimeType = file.mimeType
            size = file.size
        }

        console.log('originalname : ' + originalname + 
                ', \nfileName : ' + fileName + 
                ', \nmimeType : ' + mimeType + 
                ', \nsize : ' + size)
                
        res.send({
            'originalname' : originalname,
            'fileName' : fileName,
            'mimeType' : mimeType,
            'size' : size,
            'status' : true
        })
    } catch (err) {
        console.log(err)
        res.send({
            'originalname' : '',
            'fileName' : '',
            'mimeType' : '',
            'size' : 0,
            'status' : false
        })
    }
})

module.exports = router