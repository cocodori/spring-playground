package hello.springmvc.upload.controller

import org.springframework.web.multipart.MultipartFile

data class ItemForm(
    val itemId: Long?,
    val itemName: String,
    val imageFiles: List<MultipartFile>,
    val attachFile: MultipartFile,
)