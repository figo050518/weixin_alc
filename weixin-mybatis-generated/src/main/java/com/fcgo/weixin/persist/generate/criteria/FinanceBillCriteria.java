package com.fcgo.weixin.persist.generate.criteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinanceBillCriteria {
    /**
    finance_bill
     */
    protected String orderByClause;

    /**
    finance_bill
     */
    protected boolean distinct;

    /**
    finance_bill
     */
    protected List<Criteria> oredCriteria;

    public FinanceBillCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table finance_bill
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNull() {
            addCriterion("CURRENT_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNotNull() {
            addCriterion("CURRENT_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceEqualTo(BigDecimal value) {
            addCriterion("CURRENT_BALANCE =", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotEqualTo(BigDecimal value) {
            addCriterion("CURRENT_BALANCE <>", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThan(BigDecimal value) {
            addCriterion("CURRENT_BALANCE >", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CURRENT_BALANCE >=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThan(BigDecimal value) {
            addCriterion("CURRENT_BALANCE <", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CURRENT_BALANCE <=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIn(List<BigDecimal> values) {
            addCriterion("CURRENT_BALANCE in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotIn(List<BigDecimal> values) {
            addCriterion("CURRENT_BALANCE not in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CURRENT_BALANCE between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CURRENT_BALANCE not between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andGenAmountIsNull() {
            addCriterion("GEN_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andGenAmountIsNotNull() {
            addCriterion("GEN_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andGenAmountEqualTo(BigDecimal value) {
            addCriterion("GEN_AMOUNT =", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountNotEqualTo(BigDecimal value) {
            addCriterion("GEN_AMOUNT <>", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountGreaterThan(BigDecimal value) {
            addCriterion("GEN_AMOUNT >", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GEN_AMOUNT >=", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountLessThan(BigDecimal value) {
            addCriterion("GEN_AMOUNT <", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GEN_AMOUNT <=", value, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountIn(List<BigDecimal> values) {
            addCriterion("GEN_AMOUNT in", values, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountNotIn(List<BigDecimal> values) {
            addCriterion("GEN_AMOUNT not in", values, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GEN_AMOUNT between", value1, value2, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GEN_AMOUNT not between", value1, value2, "genAmount");
            return (Criteria) this;
        }

        public Criteria andGenEventIsNull() {
            addCriterion("GEN_EVENT is null");
            return (Criteria) this;
        }

        public Criteria andGenEventIsNotNull() {
            addCriterion("GEN_EVENT is not null");
            return (Criteria) this;
        }

        public Criteria andGenEventEqualTo(Integer value) {
            addCriterion("GEN_EVENT =", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventNotEqualTo(Integer value) {
            addCriterion("GEN_EVENT <>", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventGreaterThan(Integer value) {
            addCriterion("GEN_EVENT >", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventGreaterThanOrEqualTo(Integer value) {
            addCriterion("GEN_EVENT >=", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventLessThan(Integer value) {
            addCriterion("GEN_EVENT <", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventLessThanOrEqualTo(Integer value) {
            addCriterion("GEN_EVENT <=", value, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventIn(List<Integer> values) {
            addCriterion("GEN_EVENT in", values, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventNotIn(List<Integer> values) {
            addCriterion("GEN_EVENT not in", values, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventBetween(Integer value1, Integer value2) {
            addCriterion("GEN_EVENT between", value1, value2, "genEvent");
            return (Criteria) this;
        }

        public Criteria andGenEventNotBetween(Integer value1, Integer value2) {
            addCriterion("GEN_EVENT not between", value1, value2, "genEvent");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("SELLER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("SELLER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Integer value) {
            addCriterion("SELLER_ID =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Integer value) {
            addCriterion("SELLER_ID <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Integer value) {
            addCriterion("SELLER_ID >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SELLER_ID >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Integer value) {
            addCriterion("SELLER_ID <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Integer value) {
            addCriterion("SELLER_ID <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Integer> values) {
            addCriterion("SELLER_ID in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Integer> values) {
            addCriterion("SELLER_ID not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Integer value1, Integer value2) {
            addCriterion("SELLER_ID between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SELLER_ID not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNull() {
            addCriterion("CREATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNotNull() {
            addCriterion("CREATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNameEqualTo(String value) {
            addCriterion("CREATE_NAME =", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotEqualTo(String value) {
            addCriterion("CREATE_NAME <>", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThan(String value) {
            addCriterion("CREATE_NAME >", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME >=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThan(String value) {
            addCriterion("CREATE_NAME <", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME <=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLike(String value) {
            addCriterion("CREATE_NAME like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotLike(String value) {
            addCriterion("CREATE_NAME not like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameIn(List<String> values) {
            addCriterion("CREATE_NAME in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotIn(List<String> values) {
            addCriterion("CREATE_NAME not in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameBetween(String value1, String value2) {
            addCriterion("CREATE_NAME between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_NAME not between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameIsNull() {
            addCriterion("UPDATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateNameIsNotNull() {
            addCriterion("UPDATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateNameEqualTo(String value) {
            addCriterion("UPDATE_NAME =", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameNotEqualTo(String value) {
            addCriterion("UPDATE_NAME <>", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameGreaterThan(String value) {
            addCriterion("UPDATE_NAME >", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_NAME >=", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameLessThan(String value) {
            addCriterion("UPDATE_NAME <", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_NAME <=", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameLike(String value) {
            addCriterion("UPDATE_NAME like", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameNotLike(String value) {
            addCriterion("UPDATE_NAME not like", value, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameIn(List<String> values) {
            addCriterion("UPDATE_NAME in", values, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameNotIn(List<String> values) {
            addCriterion("UPDATE_NAME not in", values, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameBetween(String value1, String value2) {
            addCriterion("UPDATE_NAME between", value1, value2, "updateName");
            return (Criteria) this;
        }

        public Criteria andUpdateNameNotBetween(String value1, String value2) {
            addCriterion("UPDATE_NAME not between", value1, value2, "updateName");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumIsNull() {
            addCriterion("BUSS_ORDER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumIsNotNull() {
            addCriterion("BUSS_ORDER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumEqualTo(String value) {
            addCriterion("BUSS_ORDER_NUM =", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumNotEqualTo(String value) {
            addCriterion("BUSS_ORDER_NUM <>", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumGreaterThan(String value) {
            addCriterion("BUSS_ORDER_NUM >", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("BUSS_ORDER_NUM >=", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumLessThan(String value) {
            addCriterion("BUSS_ORDER_NUM <", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumLessThanOrEqualTo(String value) {
            addCriterion("BUSS_ORDER_NUM <=", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumLike(String value) {
            addCriterion("BUSS_ORDER_NUM like", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumNotLike(String value) {
            addCriterion("BUSS_ORDER_NUM not like", value, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumIn(List<String> values) {
            addCriterion("BUSS_ORDER_NUM in", values, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumNotIn(List<String> values) {
            addCriterion("BUSS_ORDER_NUM not in", values, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumBetween(String value1, String value2) {
            addCriterion("BUSS_ORDER_NUM between", value1, value2, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderNumNotBetween(String value1, String value2) {
            addCriterion("BUSS_ORDER_NUM not between", value1, value2, "bussOrderNum");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeIsNull() {
            addCriterion("BUSS_ORDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeIsNotNull() {
            addCriterion("BUSS_ORDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeEqualTo(Integer value) {
            addCriterion("BUSS_ORDER_TYPE =", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeNotEqualTo(Integer value) {
            addCriterion("BUSS_ORDER_TYPE <>", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeGreaterThan(Integer value) {
            addCriterion("BUSS_ORDER_TYPE >", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUSS_ORDER_TYPE >=", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeLessThan(Integer value) {
            addCriterion("BUSS_ORDER_TYPE <", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("BUSS_ORDER_TYPE <=", value, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeIn(List<Integer> values) {
            addCriterion("BUSS_ORDER_TYPE in", values, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeNotIn(List<Integer> values) {
            addCriterion("BUSS_ORDER_TYPE not in", values, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("BUSS_ORDER_TYPE between", value1, value2, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andBussOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("BUSS_ORDER_TYPE not between", value1, value2, "bussOrderType");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table finance_bill
     *
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table finance_bill
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}