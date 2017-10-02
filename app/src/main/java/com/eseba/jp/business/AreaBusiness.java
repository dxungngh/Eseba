package com.eseba.jp.business;

import android.text.TextUtils;

import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.database.datasource.AreaDataSource;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.listener.OnGetAreaListListener;
import com.eseba.jp.network.AreaNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielnguyen on 9/17/17.
 */

public class AreaBusiness {
    public static final String TAG = AreaBusiness.class.getSimpleName();

    private AreaNetwork network;
    private AreaDataSource dataSource;

    public AreaBusiness() {
        this.network = (AreaNetwork) ServiceRegistry.getService(AreaNetwork.TAG);
        this.dataSource = (AreaDataSource) ServiceRegistry.getService(AreaDataSource.TAG);
    }

    public void getAreaList(final OnGetAreaListListener listener) {
        this.network.getAreaList(new OnGetAreaListListener() {
            @Override
            public void onSuccess(List<Area> result) {
                if (result != null && result.size() > 0) {
                    saveAreaList(result);
                }
                listener.onSuccess(dataSource.getAllAreas());
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }

    private void saveAreaList(List<Area> serverAreaList) {
        List<Area> localAreaList = this.dataSource.getAllAreas();
        for (Area serverArea : serverAreaList) {
            if (serverArea != null && !TextUtils.isEmpty(serverArea.getAreaCode())) {
                boolean isExisted = false;
                if (localAreaList != null && localAreaList.size() > 0) {
                    for (Area localArea : localAreaList) {
                        if (localArea != null && !TextUtils.isEmpty(localArea.getAreaCode())) {
                            if (serverArea.getAreaCode().equals(localArea.getAreaCode())) {
                                this.dataSource.updateArea(localArea.getId(), serverArea);
                                isExisted = true;
                                break;
                            }
                        }
                    }
                }
                if (!isExisted) {
                    this.dataSource.createArea(serverArea);
                }
            }
        }
    }

    public List<String> getAreaGroupListOnDatabase() {
        List<String> areaGroupNameList = new ArrayList<>();
        List<Area> areaList = this.dataSource.getAllAreas();
        if (areaList != null && areaList.size() > 0) {
            for (Area area : areaList) {
                String areaGroupName = area.getAreaGroupName();
                if (!TextUtils.isEmpty(areaGroupName) && !areaGroupNameList.contains(areaGroupName)) {
                    areaGroupNameList.add(areaGroupName);
                }
            }
        }
        return areaGroupNameList;
    }

    public List<Area> getAreaListOfGroup(String areaGroupName) {
        List<Area> list = new ArrayList<>();
        List<Area> areaList = this.dataSource.getAllAreas();
        if (areaList != null && areaList.size() > 0) {
            for (Area area : areaList) {
                String name = area.getAreaGroupName();
                if (!TextUtils.isEmpty(name) && !name.equals(areaGroupName)) {
                    list.add(area);
                }
            }
        }
        return list;
    }

    public void saveArea(Area area) {
        this.dataSource.updateArea(area.getId(), area);
    }
}
