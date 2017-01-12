package com.mvc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mvc.dao.WorkHouseDao;
import com.utils.Pager;

/**
 * 部门员工做房统计数据持久层实现
 * 
 * @author wangrui
 * @date 2016-12-08
 */
@Repository("workHouseDaoImpl")
public class WorkHouseDaoImpl implements WorkHouseDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	// 查询员工做房
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectWorkHouse(Map<String, Object> map) {
		EntityManager em = emf.createEntityManager();
		String sqlLimit = workHouseSQL(map);

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select a.Staff_name,a.Staff_no,coalesce(b.mochen_num,0) mochen_num,coalesce(b.mochen,0) mochen,coalesce(c.guoye_num,0) guoye_num,");
		sql.append("coalesce(c.guoye,0) guoye,coalesce(d.litui_num,0) litui_num,coalesce(d.litui,0) litui from ");
		sql.append(
				"(select s.Staff_no,s.Staff_name from case_info cs left join staff_info s on s.Staff_id=cs.case_author");
		sql.append(" where s.Staff_no is not null " + sqlLimit + " group by s.Staff_no) as a left join ");
		sql.append(
				"(select s.Staff_no,count(1) mochen_num,sum(cs.use_time) mochen from case_info cs left join staff_info s on s.Staff_id=cs.case_author");
		sql.append(
				" where clean_type=0 " + sqlLimit + " group by s.Staff_no) as b on b.Staff_no=a.Staff_no left join ");
		sql.append(
				"(select s.Staff_no,count(1) guoye_num,sum(cs.use_time) guoye from case_info cs left join staff_info s on s.Staff_id=cs.case_author");
		sql.append(
				" where clean_type=1 " + sqlLimit + " group by s.Staff_no) as c on c.Staff_no=a.Staff_no left join ");
		sql.append(
				"(select s.Staff_no,count(1) litui_num,sum(cs.use_time) litui from case_info cs left join staff_info s on s.Staff_id=cs.case_author");
		sql.append(" where clean_type=2 " + sqlLimit + " group by s.Staff_no) as d on d.Staff_no=a.Staff_no");
		Query query = em.createNativeQuery(sql.toString());
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	/**
	 * 员工做房SQL条件
	 * 
	 * @param map
	 * @return
	 */
	private String workHouseSQL(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();

		String roomType = (String) map.get("roomType");
		String startTime = (String) map.get("startTime");
		String endTime = (String) map.get("endTime");
		String deptId = (String) map.get("deptId");
		String cleanType = (String) map.get("cleanType");
		String staffId = (String) map.get("staffId");

		if (roomType != null) {
			sql.append(" and cs.sort_no='" + roomType + "'");
		}
		if (startTime != null && endTime != null) {
			sql.append(" and cs.open_time between '" + startTime + "'" + " and '" + endTime + "'");
		}
		if (deptId != null) {
			sql.append(" and cs.depart_id='" + deptId + "'");
		}
		if (cleanType != null) {
			sql.append(" and cs.clean_type=" + cleanType);
		}
		if (staffId != null) {
			sql.append(" and cs.case_author='" + staffId + "'");
		}
		return sql.toString();
	}

	/**
	 * 分页SQL条件
	 * 
	 * @param pager
	 * @return
	 */
	@SuppressWarnings("unused")
	private String pageSQL(Pager pager) {
		String str = "";
		Integer offset = null;
		Integer end = null;
		if (pager != null) {
			offset = pager.getOffset();
			end = pager.getPageSize();
		}
		if (offset != null && end != null) {
			str = " limit " + offset + "," + end;
		}
		return str;
	}

	// 获取全体员工平均做房用时
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectAllAverWorkTime(Map<String, Object> map) {
		EntityManager em = emf.createEntityManager();
		String sqlLimit = workHouseSQL(map);

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select cs.case_author,coalesce(count(1),0) num,coalesce(sum(cs.use_time),0) use_time from case_info cs");
		sql.append(" where 1=1 " + sqlLimit + " group by cs.case_author");
		Query query = em.createNativeQuery(sql.toString());
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	// 获取员工每个月做房用时
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectMonthWorkTime(Map<String, Object> map) {
		EntityManager em = emf.createEntityManager();
		String sqlLimit = workHouseSQL(map);

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select DATE_FORMAT(cs.open_time,'%m') months,coalesce(sum(cs.use_time),0) use_time from case_info cs");
		sql.append(" where 1=1 " + sqlLimit + " group by months");
		Query query = em.createNativeQuery(sql.toString());
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

}
