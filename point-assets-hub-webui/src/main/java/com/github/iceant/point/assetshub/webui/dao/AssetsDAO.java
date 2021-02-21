package com.github.iceant.point.assetshub.webui.dao;

import com.github.iceant.point.assetshub.webui.dos.AssetsDO;
import com.github.iceant.point.assetshub.webui.page.Page;
import com.github.iceant.point.assetshub.webui.page.PageRequest;
import com.github.iceant.point.assetshub.webui.page.SqlitePageFunction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AssetsDAO {
    private final JdbcTemplate jdbcTemplate;

    public AssetsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public static ResultSetExtractor<AssetsDO> makeResultSetExtractor() {
        return new ResultSetExtractor<AssetsDO>() {
            @Override
            public AssetsDO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    AssetsDO obj = new AssetsDO();
                    obj.setId(resultSet.getLong("id"));
                    obj.setType(resultSet.getString("type"));
                    obj.setGroupId(resultSet.getString("groupId"));
                    obj.setArtifactId(resultSet.getString("artifactId"));
                    obj.setVersion(resultSet.getString("version"));
                    obj.setCreateDatetime(resultSet.getDate("createDatetime"));
                    obj.setDetail(resultSet.getString("detail"));
                    return obj;
                } else {
                    return null;
                }
            }
        };
    }

    public static RowMapper<AssetsDO> makeRowMapper() {
        return new RowMapper<AssetsDO>() {
            @Override
            public AssetsDO mapRow(ResultSet resultSet, int i) throws SQLException {
                AssetsDO obj = new AssetsDO();
                obj.setId(resultSet.getLong("id"));
                obj.setType(resultSet.getString("type"));
                obj.setGroupId(resultSet.getString("groupId"));
                obj.setArtifactId(resultSet.getString("artifactId"));
                obj.setVersion(resultSet.getString("version"));
                obj.setCreateDatetime(resultSet.getDate("createDatetime"));
                obj.setDetail(resultSet.getString("detail"));
                return obj;
            }
        };
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////
    public Page<List<AssetsDO>> searchByExample(AssetsDO obj, PageRequest pageRequest) {
        if (obj == null) return Page.makeEmpty(pageRequest.getPageNumber(), pageRequest.getPageSize());

        if (obj.getId() != null) {
            AssetsDO ret = findByPk(obj.getId());
            return Page.makePage(pageRequest.getPageNumber(), pageRequest.getPageSize(), Arrays.asList(ret), 1L);
        } else {
            StringBuilder sql = new StringBuilder("SELECT * FROM assets WHERE 1>0 ");
            List params = new ArrayList();
            if (obj.getType() != null && obj.getType().length() > 0) {
                sql.append(" AND ").append("type=?");
                params.add(obj.getType());
            }
            if (obj.getGroupId() != null && obj.getGroupId().length() > 0) {
                sql.append(" AND ").append("groupId=?");
                params.add(obj.getGroupId());
            }
            if (obj.getArtifactId() != null && obj.getArtifactId().length() > 0) {
                sql.append(" AND ").append("artifactId=?");
                params.add(obj.getArtifactId());
            }
            if (obj.getVersion() != null && obj.getVersion().length() > 0) {
                sql.append(" AND ").append("version=?");
                params.add(obj.getVersion());
            }
            if (obj.getCreateDatetime() != null) {
                sql.append(" AND ").append("createDatetime=?");
                params.add(obj.getCreateDatetime());
            }
            sql.append(" ORDER BY artifactId, groupId");

            return new SqlitePageFunction<AssetsDO>(sql.toString(), params, jdbcTemplate, makeRowMapper()).getPage(pageRequest);

        }
    }


    ////////////////////////////////////////////////////////////////////////////////
    ////
    public AssetsDO insert(AssetsDO obj) {
        int ret = jdbcTemplate.update("INSERT INTO assets(type, groupId, artifactId, version, createDatetime, detail) VALUES(?, ?, ?, ?, ?, ?)"
                , obj.getType()
                , obj.getGroupId()
                , obj.getArtifactId()
                , obj.getVersion()
                , obj.getCreateDatetime()
                , obj.getDetail()
        );
        Long id = jdbcTemplate.query("SELECT seq FROM sqlite_sequence WHERE name=?", new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.getLong(1);
            }
        }, "assets");

        return obj.setId(id);
    }

    public AssetsDO update(AssetsDO obj) {
        if (obj.getId() == null) return null;
        int ret = jdbcTemplate.update("UPDATE assets SET type=?, groupId=?, artifactId=?, version=?, createDatetime=?, detail=? WHERE id=?"
                , obj.getType()
                , obj.getGroupId()
                , obj.getArtifactId()
                , obj.getVersion()
                , obj.getCreateDatetime()
                , obj.getDetail()
                , obj.getId()
        );
        return obj;
    }

    public AssetsDO delete(AssetsDO obj) {
        if (obj.getId() == null) return obj;
        int ret = jdbcTemplate.update("DELETE FROM assets WHERE id=?", obj.getId());
        if (ret == 0) return null;
        return obj;
    }

    public AssetsDO deleteByPk(Serializable id) {
        if (id == null) return null;
        AssetsDO obj = findByPk(id);
        if (obj == null) return null;
        return delete(obj);
    }

    private AssetsDO findByPk(Serializable id) {
        return jdbcTemplate.query("SELECT * FROM assets WHERE id=?", makeResultSetExtractor(), id);
    }
}
