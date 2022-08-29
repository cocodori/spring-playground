package hello.springmvc.upload.controller

import hello.springmvc.upload.domain.Item
import hello.springmvc.upload.domain.ItemRepo
import hello.springmvc.upload.file.FileStore
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.util.UriUtils
import java.nio.charset.StandardCharsets

@Controller
class ItemController(
    private val itemRepository: ItemRepo,
    private val fileStore: FileStore
    ) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/items/new")
    fun newItem(
//        @ModelAttribute form: ItemForm
    ) = "item-form"

    @PostMapping("/items/new")
    fun saveItem(
        @ModelAttribute form: ItemForm,
        redirectAttributes: RedirectAttributes
    ): String {
        val attachFile = fileStore.storeFile(form.attachFile)
        val storeImageFiles = fileStore.storeFiles(form.imageFiles)


        val item = Item(
            itemName = form.itemName,
            attachFile = attachFile,
            imageFiles = storeImageFiles,
        )

        itemRepository.save(item)

        redirectAttributes.addAttribute("itemId", item.id)

        return "redirect:/items/{itemId}"
    }

    @GetMapping("/items/{id}")
    fun items(
        @PathVariable id: Long,
        model: Model
    ): String {
        val item = itemRepository.findById(id)
        model["item"] = item ?: throw RuntimeException()

        return "item-view"
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    fun downloadImage(
        @PathVariable filename: String
    ): Resource {
        return UrlResource("file:${fileStore.getPullPath(filename)}")
    }

    @GetMapping("/attach/{itemId}")
    fun downloadAttach(
        @PathVariable itemId: Long
    ): ResponseEntity<Resource> {
        val item = itemRepository.findById(itemId) ?: throw RuntimeException()
        val storeFileName = item.attachFile!!.storeFileName
        val uploadFileName = item.attachFile!!.uploadFileName

        val resource = UrlResource("file:${fileStore.getPullPath(storeFileName)}")

        log.info("uploadFileName={}", uploadFileName)
        val encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8)
        val contentDisposition = "attachment; filename=\"$encodedUploadFileName\""

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .body(resource)
    }
}