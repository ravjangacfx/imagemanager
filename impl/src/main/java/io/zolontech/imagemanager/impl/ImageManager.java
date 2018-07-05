//
//  This file was auto-generated by Macaw tools 0.9.7-SNAPSHOT version built on Fri, 13 Apr 2018 08:12:31 +0530 
//
package io.zolontech.imagemanager.impl;

import java.lang.Override;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import io.zolontech.imagemanager.Approval;
import io.zolontech.imagemanager.ApprovalStatus;
import io.zolontech.imagemanager.DomainEntityInstantiator;
import io.zolontech.imagemanager.Image;
import io.zolontech.imagemanager.User;

public class ImageManager implements com.cfx.service.api.Service, io.zolontech.imagemanager.ImageManager {

	private Map<String, io.zolontech.imagemanager.User> users = new HashMap<>();

	private Map<String, io.zolontech.imagemanager.Approval> approvals = new HashMap<>();
	
  @Override
  public void initialize(com.cfx.service.api.config.Configuration config) throws com.cfx.service.api.ServiceException {
  }

  @Override
  public void start(com.cfx.service.api.StartContext startContext) throws com.cfx.service.api.ServiceException {
  }

  @Override
  public void stop(com.cfx.service.api.StopContext stopContext) throws com.cfx.service.api.ServiceException {
  }

  @Override
  public String uploadImage(String userCode, String imageUrl) {
	  Approval approval = DomainEntityInstantiator.getInstance().newInstance(Approval.class);
	  approval.setImageUrl(imageUrl);
	  approval.setUser(users.get(userCode));
	  approval.setStatus(ApprovalStatus.PENDING);
	  approvals.put(imageUrl, approval);
    return imageUrl;
  }

  @Override
  public String addUser(io.zolontech.imagemanager.User user) {
	  users.put(user.getUserCode(), user);
    return user.getUserCode();
  }

  @Override
  public io.zolontech.imagemanager.Approval updateApproval(io.zolontech.imagemanager.Approval approval) {
	  approvals.put(approval.getImageUrl(), approval);
    return approval;
  }

  @Override
  public java.util.List<io.zolontech.imagemanager.Approval> getApprovals(io.zolontech.imagemanager.ApprovalStatus status) {
	  List<Approval> tmpApprovals = new ArrayList<>();
	  for (Approval approval : approvals.values()) {
		  if (ApprovalStatus.ALL.name().equals(status.name()) || approval.getStatus().name().equals(status.name())) {
			  tmpApprovals.add(approval);
		  }
	  }
	  
    return tmpApprovals;
  }

@Override
public List<Approval> getImages(String userCode) {
	  List<Approval> tmpApprovals = new ArrayList<>();
	  for (Approval approval : approvals.values()) {
		  if (approval.getUser().getUserCode().equals(userCode)) {
			  tmpApprovals.add(approval);
		  }
	  }
	  
  return tmpApprovals;
}

@Override
public List<User> getUsers() {
	List<User> userList = new ArrayList<>();
	userList.addAll(this.users.values());
	return userList;
}
}
