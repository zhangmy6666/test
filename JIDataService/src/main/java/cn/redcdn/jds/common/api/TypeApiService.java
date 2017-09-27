package cn.redcdn.jds.common.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.redcdn.jds.app.dao.VipProbationExtendDao;
import cn.redcdn.jds.common.entity.VipProbation;
import cn.redcdn.jds.common.util.Constants;
import cn.redcdn.jds.common.util.DateUtil;

/**
 * <dl>
 * <dt>TypeApiService.java</dt>
 * <dd>Description: 取得用户类型</dd>
 * <dd>Copyright: Copyright (C) 2017</dd>
 * <dd>Company: 北京红云融通技术有限公司</dd>
 * <dd>CreateDate: 2017-2-22</dd>
 * </dl>
 * 
 * @author chenzhc
 */
@Service
public class TypeApiService extends BaseApiService {
	
	@Autowired 
	VipProbationExtendDao vipProbationExtendDao;
	
	/**
     * 判断用户类型
     * 
     * @param proDate 试用截止日期
     * @param expiryDate 服务有效日期
     * @return DB查询的账号数据
     */
	public String getUserType(Date proDate, Date expiryDate) {
		
		String sysDateStr = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD_H);
		if (expiryDate != null) {
			String expiryDateStr = DateUtil.convertDateToString(expiryDate, DateUtil.DATE_FORMAT_YYYYMMDD_H);
			if (sysDateStr.compareTo(expiryDateStr) <= 0) {
				return "1";
			}
		}
		String proDateStr = DateUtil.convertDateToString(proDate, DateUtil.DATE_FORMAT_YYYYMMDD_H);
		if (expiryDate == null && sysDateStr.compareTo(proDateStr) <= 0) {
			return "2";
		}
		
		return "3";
	}
	
	/**
     * 获得试用载止日期
     * 
     * @param proDate 试用截止日期
     * @param expiryDate 服务有效日期
     * @return DB查询的账号数据
     */
	public Date getProDate() {
		
		List<VipProbation> list = vipProbationExtendDao.queryAll();
		if (!CollectionUtils.isEmpty(list)) {
			VipProbation vipProbation = list.get(0);
			if (vipProbation.getProMode() == Constants.VIP_PRO_DAY) {
				int days = vipProbation.getProDay();
				return DateUtil.getDateAddDay(new Date(), days);
			} else {
				return vipProbation.getProDate();
			}
			
		} else {
			return DateUtil.getDateAddDay(new Date(), 14);
		}
	}
}
