package com.helpe.YoHelper.service.abstractService;

import com.helpe.YoHelper.model.request.UserRequest;
import com.helpe.YoHelper.model.response.UserResponse;

public interface IUserService extends CRUDService<UserRequest, UserResponse,Long> {
}
