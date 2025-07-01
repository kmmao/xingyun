package com.lframework.xingyun.settle.service;

import com.lframework.starter.web.core.components.resp.PageResult;
import com.lframework.starter.web.core.service.BaseMpService;
import com.lframework.xingyun.settle.dto.sheet.customer.CustomerSettleBizItemDto;
import com.lframework.xingyun.settle.dto.sheet.customer.CustomerSettleSheetFullDto;
import com.lframework.xingyun.settle.entity.CustomerSettleSheet;
import com.lframework.xingyun.settle.vo.sheet.customer.ApprovePassCustomerSettleSheetVo;
import com.lframework.xingyun.settle.vo.sheet.customer.ApproveRefuseCustomerSettleSheetVo;
import com.lframework.xingyun.settle.vo.sheet.customer.CreateCustomerSettleSheetVo;
import com.lframework.xingyun.settle.vo.sheet.customer.QueryCustomerSettleSheetVo;
import com.lframework.xingyun.settle.vo.sheet.customer.QueryCustomerUnSettleBizItemVo;
import com.lframework.xingyun.settle.vo.sheet.customer.UpdateCustomerSettleSheetVo;
import java.util.List;

public interface CustomerSettleSheetService extends BaseMpService<CustomerSettleSheet> {

    /**
     * 查询列表
     *
     * @param pageIndex
     * @param pageSize
     * @param vo
     * @return
     */
    PageResult<CustomerSettleSheet> query(Integer pageIndex, Integer pageSize,
        QueryCustomerSettleSheetVo vo);

    /**
     * 查询列表
     *
     * @param vo
     * @return
     */
    List<CustomerSettleSheet> query(QueryCustomerSettleSheetVo vo);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    CustomerSettleSheetFullDto getDetail(String id);

    /**
     * 创建
     *
     * @param vo
     * @return
     */
    String create(CreateCustomerSettleSheetVo vo);

    /**
     * 修改
     *
     * @param vo
     */
    void update(UpdateCustomerSettleSheetVo vo);

    /**
     * 审核通过
     *
     * @param vo
     */
    void approvePass(ApprovePassCustomerSettleSheetVo vo);

    /**
     * 直接审核通过
     *
     * @param vo
     */
    String directApprovePass(CreateCustomerSettleSheetVo vo);

    /**
     * 审核拒绝
     *
     * @param vo
     */
    void approveRefuse(ApproveRefuseCustomerSettleSheetVo vo);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 查询业务单据
     *
     * @param id
     * @return
     */
    CustomerSettleBizItemDto getBizItem(String id);

    /**
     * 更新业务单据未结算
     *
     * @param id
     */
    void setBizItemUnSettle(String id);

    /**
     * 更新业务单据结算中
     *
     * @param id
     */
    void setBizItemPartSettle(String id);

    /**
     * 更新业务单据已结算
     *
     * @param id
     */
    void setBizItemSettled(String id);

    /**
     * 查询未结算单据
     *
     * @param vo
     * @return
     */
    List<CustomerSettleBizItemDto> getUnSettleBizItems(QueryCustomerUnSettleBizItemVo vo);
}
