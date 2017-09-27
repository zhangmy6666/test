package cn.redcdn.jds.systemadmin.service;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.dao.VipProbationDao;
import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.VipProbation;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;
import cn.redcdn.jds.common.util.Constants;
import cn.redcdn.jds.common.util.DateUtil;
import cn.redcdn.jds.common.util.MessageUtil;
import cn.redcdn.jds.common.util.StringUtil;
import cn.redcdn.jds.systemadmin.dao.AdminVipProbationExtendDao;

@Service("adminSetVIPProbation")
public class AdminSetVIPProbationService extends BaseService<String> {

	@Autowired
	private TokenApiService tokenApiService;

	@Autowired
	private VipProbationDao vipProbationDao;

	@Autowired
	private AdminVipProbationExtendDao vipProbationExtendDao;

	@Override
	public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
		// 必须check
		CheckUtil.checkEmpty(params, "token");

		// token校验
		String token = params.getString("token");
		tokenApiService.checkSystemLoginToken(token);

		String proDay = params.getString("proDay");
		String proDate = params.getString("proDate");

		VipProbation vipPro = new VipProbation();
		if (StringUtil.isNotBlank(proDay)) {
			vipPro.setProMode(Constants.VIP_PRO_DAY);
			vipPro.setProDay(Integer.parseInt(proDay));
		} else if (StringUtil.isNotBlank(proDate)) {
			vipPro.setProMode(Constants.VIP_PRO_DATE);
			vipPro.setProDate(DateUtil.convertSecondsStringToDayStart(proDate));
		} else {
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("params.empty");
			String message = messageInfo.getMsg();
			message = MessageFormat.format(message, "proDay or proDate");
			messageInfo.setMsg(message);

			throw new ExternalServiceException(messageInfo);
		}
		vipProbationExtendDao.delete();
		vipProbationDao.insert(vipPro);

		// 参数获取
		ResponseDto<String> rspDto = new ResponseDto<String>();
		return rspDto;
	}
}
