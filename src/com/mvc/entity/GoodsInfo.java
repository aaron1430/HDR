package com.mvc.entity;

import javax.persistence.*;

/**
 * 物品
 * 
 * @author wanghuimin
 * @date 2016年12月8日
 */
@Entity
@Table(name = "goods_info")
public class GoodsInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer goods_id;// 物品ID，主键

	@Column(nullable = false, length = 100)
	private String goods_Name;// 物品中文名称

	@Column(nullable = false, length = 100)
	private String goods_Ename;// 物品英文名称

	@Column(length = 45)
	private String code;// code值，一般根据酒馆而定

	@Lob
	private String description;// 描述

	@Column(nullable = false)
	private Integer display;// 显示标志，1显示、0不显示

	@Column(length = 100)
	private String goods_Picture;// 物品图片

	@Column(nullable = false, length = 30)
	private String goods_Price;// 物品价钱

	@Column(nullable = false, length = 30)
	private String goods_Typeid;// 物品类型

	@Column(nullable = false)
	private Integer goodsOrder;// 显示排序

	@Column(nullable = false)
	private Integer gWith;// 图片宽度

	private Integer maxNum;// 最大可用数量，-1代表不限使用数量

	private Integer newOrder;// 耗品领用排序

	@Column(nullable = false, length = 45)
	private String PC_Code;

	@Column(length = 145)
	private String spec_cn;

	@Column(length = 145)
	private String spec_en;

	@Column(nullable = false)
	private Integer totalOrder;

	@Column(length = 20)
	private String unit_cn;

	@Column(length = 30)
	private String unit_en;

	private Integer useNum;

	public GoodsInfo() {
	}

	public Integer getGoods_id() {
		return this.goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDisplay() {
		return this.display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getGoods_Ename() {
		return this.goods_Ename;
	}

	public void setGoods_Ename(String goods_Ename) {
		this.goods_Ename = goods_Ename;
	}

	public String getGoods_Name() {
		return this.goods_Name;
	}

	public void setGoods_Name(String goods_Name) {
		this.goods_Name = goods_Name;
	}

	public String getGoods_Picture() {
		return this.goods_Picture;
	}

	public void setGoods_Picture(String goods_Picture) {
		this.goods_Picture = goods_Picture;
	}

	public String getGoods_Price() {
		return this.goods_Price;
	}

	public void setGoods_Price(String goods_Price) {
		this.goods_Price = goods_Price;
	}

	public String getGoods_Typeid() {
		return this.goods_Typeid;
	}

	public void setGoods_Typeid(String goods_Typeid) {
		this.goods_Typeid = goods_Typeid;
	}

	public Integer getGoodsOrder() {
		return this.goodsOrder;
	}

	public void setGoodsOrder(Integer goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Integer getGWith() {
		return this.gWith;
	}

	public void setGWith(Integer gWith) {
		this.gWith = gWith;
	}

	public Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getNewOrder() {
		return this.newOrder;
	}

	public void setNewOrder(Integer newOrder) {
		this.newOrder = newOrder;
	}

	public String getPC_Code() {
		return this.PC_Code;
	}

	public void setPC_Code(String PC_Code) {
		this.PC_Code = PC_Code;
	}

	public String getSpec_cn() {
		return this.spec_cn;
	}

	public void setSpec_cn(String spec_cn) {
		this.spec_cn = spec_cn;
	}

	public String getSpec_en() {
		return this.spec_en;
	}

	public void setSpec_en(String spec_en) {
		this.spec_en = spec_en;
	}

	public Integer getTotalOrder() {
		return this.totalOrder;
	}

	public void setTotalOrder(Integer totalOrder) {
		this.totalOrder = totalOrder;
	}

	public String getUnit_cn() {
		return this.unit_cn;
	}

	public void setUnit_cn(String unit_cn) {
		this.unit_cn = unit_cn;
	}

	public String getUnit_en() {
		return this.unit_en;
	}

	public void setUnit_en(String unit_en) {
		this.unit_en = unit_en;
	}

	public Integer getUseNum() {
		return this.useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

}