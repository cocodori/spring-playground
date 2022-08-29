package hello.springmvc.upload.domain

data class Item(
    var id: Long? = null,
    val itemName: String,
    val attachFile: UploadFile?,
    val imageFiles: List<UploadFile?>
)