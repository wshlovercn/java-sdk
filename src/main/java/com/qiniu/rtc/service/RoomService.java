package com.qiniu.rtc.service;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.rtc.model.RoomParam;
import com.qiniu.rtc.model.UrlParam;
import com.qiniu.rtc.model.RoomAccess;
import com.qiniu.util.Auth;

/**
 * 房间相关处理的api
 */
public class RoomService extends AbstractService {

    /**
     * 初始化
     *
     * @param auth
     */
    public RoomService(Auth auth) {
        super(auth);
    }


    /**
     * 创建房间
     *
     * @param appId
     * @param roomParam
     * @return
     * @throws QiniuException
     */
    public Response createRoom(String appId, RoomParam roomParam) throws QiniuException {
        String urlPattern = "/v3/apps/%s/createroom";
        return postCall(roomParam, urlPattern, appId);
    }

    /**
     * 删除房间
     *
     * @param appId
     * @param roomName
     * @return
     * @throws QiniuException
     */
    public Response deleteRoom(String appId, String roomName) throws QiniuException {
        String urlPattern = "/v3/apps/%s/rooms/%s";
        return deleteCall(null, urlPattern, appId, roomName);
    }

    /**
     * @param appId    房间所属帐号的 app
     * @param roomName 操作所查询的连麦房间
     * @param appId
     * @param roomName
     * @return Response      如果不读取Response的数据，请注意调用Close方法关闭
     * @return
     * @throws QiniuException
     */
    public Response listUser(String appId, String roomName) throws QiniuException {
        String urlPattern = "/v3/apps/%s/rooms/%s/users";
        return getCall(urlPattern, appId, roomName);
    }

    /**
     * 踢出房间内的某个用户
     *
     * @param appId
     * @param roomName
     * @param userId
     * @return
     * @throws QiniuException
     */
    public Response kickUser(String appId, String roomName, String userId) throws QiniuException {
        String urlPattern = "/v3/apps/%s/rooms/%s/users/%s";
        return deleteCall(null, urlPattern, appId, roomName, userId);
    }

    /**
     * 获取当前所有活跃的房间
     *
     * @param roomNamePrefix
     * @param urlParam       分页参数
     * @return
     * @throws QiniuException
     */
    public Response listActiveRoom(String appId, String roomNamePrefix, UrlParam urlParam) throws QiniuException {
        String urlPattern = "/v3/apps/%s/rooms?prefix=%s&offset=%d&limit=%d";
        return getCall(urlPattern, appId, roomNamePrefix, urlParam.getOffset(), urlParam.getLimit());
    }

    /**
     * 获取房间TOKEN
     *
     * @return roomToken 房间token
     * @throws Exception 未知异常
     */
    public String getRoomToken(RoomAccess access) throws Exception {
        String json = gson.toJson(access);
        return auth.signRoomToken(json);
    }
}
