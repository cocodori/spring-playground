package hello.springmvc.upload.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/servlet/v2")
class ServletUploadControllerV2 {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${file.dir}")
    private lateinit var fileDir: String

    @GetMapping("/upload")
    fun newFile(): String {
        return "upload-form"
    }

    @PostMapping("/upload")
    fun saveFile(
        request: HttpServletRequest
    ): String {
        log.info("request={}", request)

        val itemName = request.getParameter("itemName")
        log.info("itemName={}", itemName)

        val parts = request.parts

        for (part in parts) {
            log.info(" ==== PART ====")
            log.info("name={}", part.name)

            val headerNames = part.headerNames

            for (headerName in headerNames) {
                log.info("header {}: {}", headerName, part.getHeader(headerName))
            }

            log.info("submittedFileName={}", part.submittedFileName)
            log.info("size={}", part.size)

            //read data
            val inputStream = part.inputStream
            val body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
            log.info("body={}", body)

            //save file
            if (StringUtils.hasText(part.submittedFileName)) {
                val fullPath = "$fileDir${part.submittedFileName}"
                log.info("파일 저장 full path={}", fullPath)
                part.write(fullPath)
            }
        }

        return "upload-form"
    }
}