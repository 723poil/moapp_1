const router = require('express').Router()
const multer = require('multer')

const storage = multer.diskStorage({
    destination: 'images/',
    filename: (req, file, cb) => {
        return crypto.psudoRandomBytes(16, (err, raw) => {
            if(err) {
                return cb(err)
            }
            return cb(null, file.originalname)
        })
    }
})

const upload = multer({ storage: storage})

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
})

module.exports = router