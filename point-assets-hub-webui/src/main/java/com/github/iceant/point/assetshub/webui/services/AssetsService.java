package com.github.iceant.point.assetshub.webui.services;

import com.github.iceant.point.assetshub.webui.dao.AssetsDAO;
import com.github.iceant.point.assetshub.webui.dos.AssetsDO;
import com.github.iceant.point.assetshub.webui.page.Page;
import com.github.iceant.point.assetshub.webui.page.PageRequest;
import com.github.iceant.point.assetshub.webui.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsService {
    private final AssetsDAO assetsDAO;


    public AssetsService(AssetsDAO assetsDAO) {
        this.assetsDAO = assetsDAO;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public AssetsDO create(String groupId, String artifactId, String version, String detail){
        AssetsDO ado = new AssetsDO();
        ado.setGroupId(groupId).setArtifactId(artifactId).setVersion(version).setDetail(detail).setCreateDatetime(DateTimeUtil.now());
        return assetsDAO.insert(ado);
    }

    public Page<List<AssetsDO>> searchByExample(AssetsDO example, PageRequest pageRequest){
        return assetsDAO.searchByExample(example, pageRequest);
    }

    public AssetsDO update(AssetsDO assetsDO){
        return assetsDAO.update(assetsDO);
    }

    public AssetsDO delete(AssetsDO assetsDO){
        return assetsDAO.delete(assetsDO);
    }

    public AssetsDO findByPk(Long id) {
        return assetsDAO.findByPk(id);
    }
}
