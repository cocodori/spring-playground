package hello.springmvc.upload.file

import hello.springmvc.upload.domain.UploadFile
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

@Component
class FileStore {

    @Value("\${file.dir}")
    private lateinit var fileDir: String

    fun getPullPath(fileName: String) = fileDir + fileName

    fun storeFiles(
        multipartFiles: List<MultipartFile>
    ): List<UploadFile?> {
        val storeFileResult = mutableListOf<UploadFile?>()

        for (multipartFile in multipartFiles) {
            if (!multipartFile.isEmpty) {
                storeFileResult.add(storeFile(multipartFile))
            }
        }

        return storeFileResult
    }

    fun storeFile(
        multipartFile: MultipartFile
    ): UploadFile? {
        if (multipartFile.isEmpty)
            return null

        val originalFileName = multipartFile.originalFilename!!
        val storeFileName = createStoreFileName(originalFileName)
        multipartFile.transferTo(File(getPullPath(storeFileName)))

        return UploadFile(
            uploadFileName = originalFileName,
            storeFileName = storeFileName
        )
    }

    private fun createStoreFileName(originalFileName: String): String {
        val ext = extractExt(originalFileName)
        val uuid = UUID.randomUUID().toString()
        return "$uuid.$ext"
    }

    private fun extractExt(originalFileName: String): Any {
        val pos = originalFileName.lastIndexOf(".")
        return originalFileName.substring(pos+1)
    }
}