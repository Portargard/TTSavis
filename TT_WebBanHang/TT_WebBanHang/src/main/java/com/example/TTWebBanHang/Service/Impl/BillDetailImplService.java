package com.example.TTWebBanHang.Service.Impl;

import com.example.TTWebBanHang.Entity.Bill;
import com.example.TTWebBanHang.Entity.BillDetail;
import com.example.TTWebBanHang.Entity.ErrorMessages;
import com.example.TTWebBanHang.Exceptions.AllServiceException;
import com.example.TTWebBanHang.Repository.BillDetailRepository;
import com.example.TTWebBanHang.Service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillDetailImplService implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail getOne(UUID id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public BillDetail add(BillDetail billDetail) {
        if(billDetailRepository.countByBillAndBook(billDetail.getBill(), billDetail.getBook())==1){
            BillDetail bd = billDetailRepository.findByBillAndBook(billDetail.getBill(), billDetail.getBook());
            int soLuong = billDetail.getSoLuong()+bd.getSoLuong();
            float tongTien = (float) (soLuong*billDetail.getBook().getGiaBan());
            if(bd.getBook().getSale()==null){
                tongTien = (float) (soLuong*billDetail.getBook().getGiaBan());
            } else {
                tongTien = (float) (soLuong*billDetail.getBook().getGiaBan()-billDetail.getBook().getSale().getGiaTri());
            }
            bd.setSoLuong(soLuong);
            bd.setTongTien(tongTien);
            return billDetailRepository.save(bd);
        }
        return billDetailRepository.save(billDetail);
    }

    @Override
    public BillDetail update(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void delete(UUID id) {
        BillDetail billDetail = billDetailRepository.findById(id).orElse(null);

    if(billDetail == null){
        throw new AllServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    billDetailRepository.deleteById(id);
    }

    @Override
    public List<BillDetail> getAllByBill(Bill bill) {
        return billDetailRepository.findAllByBill(bill);
    }
}
