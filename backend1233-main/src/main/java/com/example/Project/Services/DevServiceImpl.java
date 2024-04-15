package com.example.Project.Services;

import com.example.Project.Dto.Dto;
import com.example.Project.Models.*;
import com.example.Project.Repositories.*;
import jakarta.validation.constraints.Null;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevServiceImpl {
    private final DevRepository devRepo;
    private final CDCRepository cdcRepository;
    @Autowired
    private EcuRepository ecuRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MajRepository majRepository;

    @Autowired
    private EtatDevRepository etatDevRepository;

    @Autowired
    private UserInterface userRepository;
    @Autowired
    private PeByMenuRepository peByMenuRepository;

    public Dev CreatDev(Dto dtocraetion) {
        Dev dev = new Dev();

        // Map fields from DevDTO to Dev
        dev.setDevname(dtocraetion.getDevname());
        dev.setDll(dtocraetion.getDll());
        dev.setDevDuplique(dtocraetion.isDevDuplique());
        dev.setDevComment(dtocraetion.getDevComment());
        dev.setNumBug(dtocraetion.getNumBug());

        dev.setCdc(cdcRepository.findById(dtocraetion.getIdCDC()).orElse(null));
        dev.setEcu(ecuRepository.findById(dtocraetion.getIdEcu()).orElse(null));
        dev.setMaj(majRepository.findById(dtocraetion.getIdMaj()).orElse(null));


        dev.setEtatdev(etatDevRepository.findById(dtocraetion.getIdEtatDev()).orElse(null));
        // dev.setUser(userRepository.findById(dtocraetion.getIdRC()).orElse(null));

        // Set other properties as needed

        // Save the Dev object
        return devRepo.save(dev);

    }

    public DevServiceImpl(DevRepository devRepo, CDCRepository cdcRepository) {
        this.devRepo = devRepo;
        this.cdcRepository = cdcRepository;
    }

    public List<Dev> getAllDev() {
        return devRepo.findAll();
    }

    public Dev saveDev(Dev dev) {
        return devRepo.save(dev);
    }

    public Optional<Dev> findById(int id) {
        return devRepo.findById(id);
    }

    public List<Dev> getDevByCdcId(int id) {
        return devRepo.findByCDC(id);
    }

    public List<Dev> getDevByMaj(Maj maj) {
        return devRepo.findByMaj(maj);
    }

    public Dev getDevById(int devId) {
        Dev dev = devRepo.findById(devId).orElse(null);
        if (dev != null) {
            Hibernate.initialize(dev.getMaj());
        }
        return dev;
    }

    public Dev DuplicateDev(Dev dev, int versionCode, int siteCode, int rcCode, int checkMenu, Boolean optVeh, boolean optCor, boolean optCdc,String textBug,String textcomment) {


        Dev duplicateddev=new Dev();

      if((dev.getMaj()).getIdMaj() != versionCode){
           if (dev.isDevDuplique()==false){
               if(checkMenu==1){
                   Menu duplicatedMenu=new Menu();
                   duplicatedMenu.setPathMenu(dev.getMenu().getPathMenu());
                   duplicatedMenu.setDateDemandeXLS(dev.getMenu().getDateDemandeXLS());
                   duplicatedMenu.setHeureDemandeXLS(dev.getMenu().getHeureDemandeXLS());
                   duplicatedMenu.setLectDef(dev.getMenu().isLectDef());
                   duplicatedMenu.setEffDef(dev.getMenu().isEffDef());
                   duplicatedMenu.setGenere(dev.getMenu().isGenere());
                   duplicatedMenu.setMessageInit(dev.getMenu().isMessageInit());
                   duplicatedMenu.setCodeMessageInit(dev.getMenu().getCodeMessageInit());
                   duplicatedMenu.setMessagePrinc(dev.getMenu().isMessagePrinc());
                   duplicatedMenu.setCodeMessagePrinc(dev.getMenu().getCodeMessagePrinc());
                   duplicatedMenu.setNoDtcNegFilter(dev.getMenu().isNoDtcNegFilter());

                   duplicateddev.setMenu(duplicatedMenu);
                   /** finding the pe-by-menu associated with the menu*/

                   PEByMenu PeByMenu=peByMenuRepository.findByIdMenu(dev.getMenu().getIdMenu());
                   /**create a new pe-by-menu*/
                   PEByMenu DuplicatedPeByMenu=new PEByMenu();
                   /**set attributes for the new pe-by-menu */
                   DuplicatedPeByMenu.setIdMenu(PeByMenu.getIdMenu());
                   DuplicatedPeByMenu.setLigne(PeByMenu.getLigne());
                   DuplicatedPeByMenu.setIdPE(PeByMenu.getIdPE());


               }

          }

      }
      duplicateddev.setDevname(dev.getDevname());
        duplicateddev.setCdc(dev.getCdc());
        duplicateddev.getEtatdev().setNomEtatDev("Création");
        duplicateddev.setDll(dev.getDll());
      duplicateddev.setUser(userRepository.findById(rcCode).get());
      duplicateddev.setEcu(dev.getEcu());
      duplicateddev.setMaj(majRepository.findById(versionCode).get());
        duplicateddev.setSites(dev.getSites());
        duplicateddev.setDevPrecedent(dev.getId());
        duplicateddev.setDevDuplique(false);
        duplicateddev.setDevEnCoursUtilisation(false);
        duplicateddev.getEtatdev().setIdEtatDev(1);
        if(optVeh==true){
            duplicateddev.getTypeDev().setIdTypeDev(2);
            duplicateddev.setNumBug(null);
        }else if (optCor==true){
            duplicateddev.getTypeDev().setIdTypeDev(3);
            if(!textBug.isEmpty()){
                duplicateddev.setNumBug(textBug);
            }else{
                duplicateddev.setNumBug(null);
            }
        }else {
            duplicateddev.getTypeDev().setIdTypeDev(4);
            duplicateddev.setNumBug(null);
        }

  duplicateddev.setDevComment(textcomment);

        return dev;
    }

}


