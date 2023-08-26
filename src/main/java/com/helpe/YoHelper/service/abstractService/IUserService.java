package com.helpe.YoHelper.service.abstractService;

import com.helpe.YoHelper.model.request.UserRequest;
import com.helpe.YoHelper.model.response.UserResponse;
import com.helpe.YoHelper.service.CRUDService;
import org.yaml.snakeyaml.events.Event;

public interface IUserService extends CRUDService<UserRequest, UserResponse,Long> {
}
