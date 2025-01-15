package org.agty.elfiumexpress.modules.express.service;

import org.agty.elfiumexpress.api.entity.SortBody;
import org.agty.elfiumexpress.modules.express.entity.ExpressPanel;
import org.agty.elfiumexpress.modules.express.repository.ExpressPanelRepo;
import org.agty.elfiumexpress.storage.entity.UploadedFile;
import org.agty.elfiumexpress.storage.repository.FileUploadRepo;
import org.agty.elfiumexpress.storage.repository.ThumbsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressPanelService {
    public final ExpressPanelRepo expressPanelRepo;
    public final ThumbsRepo thumbsRepo;
    public final FileUploadRepo fileUploadRepo;

    public ExpressPanelService(ExpressPanelRepo expressPanelRepo, ThumbsRepo thumbsRepo, FileUploadRepo fileUploadRepo) {
        this.expressPanelRepo = expressPanelRepo;
        this.thumbsRepo = thumbsRepo;
        this.fileUploadRepo = fileUploadRepo;
    }

    public Long saveExpressPanel(ExpressPanel expressPanel) {
        return expressPanelRepo.save(expressPanel);
    }

    public ExpressPanel getExpressPanel(Long id) {
        return expressPanelRepo.getById(id);
    }

    public List<ExpressPanel> getExpressPanels(Long idGroup) {
        return expressPanelRepo.findAll(idGroup);
    }

    public List<UploadedFile> getFiles(Long idPanel) {
        return expressPanelRepo.findUploadedFiles(idPanel);
    }

    public void del(Long idPanel) {
        removeFiles(idPanel); //Сначала файлы
        expressPanelRepo.del(idPanel); //Потом панель
    }

    public void removeFiles(Long idPanel) {
        List<UploadedFile> uploadedFiles = getFiles(idPanel);
        for (UploadedFile uploadedFile : uploadedFiles) {
            thumbsRepo.deleteThumbByFile(uploadedFile.getFile());
            fileUploadRepo.deleteFile(uploadedFile, 0L);
        }
    }

    public void sort(SortBody[] bodies) {
        expressPanelRepo.sort(bodies);
    }
}
