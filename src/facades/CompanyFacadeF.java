package facades;

import java.util.Collection;
import java.util.HashSet;

import beans.Coupon;
import couponSystemException.CouponSystemException;
import dao.CuoponDBDAO;
import dao.HelperMethodsDAO;

public class CompanyFacadeF extends Facade {
	private long companyIdLogged; // ID of the company that is logged in the system

	// constructor , gets ID number of company reference
	public CompanyFacadeF(long companyIdLogged) {
		this.companyIdLogged = companyIdLogged;
	}

	HelperMethodsDAO helperDAO = new HelperMethodsDAO();
	CuoponDBDAO couponDAO = new CuoponDBDAO();

	// tested works
	public void createCoupon(Coupon coupon) {
		try {
			if (helperDAO.ifExist("COUPON", "TITLE", coupon.getTitle())) { // need test
				System.out.println("Coupon with name = " + coupon.getTitle() + "  already exsists");
			} else {
				coupon.setId(helperDAO.getId("COUPON_ID")); // gets last coupon ID per order
				couponDAO.createCoupon(coupon);
				helperDAO.createCopuon(String.valueOf(companyIdLogged), String.valueOf(coupon.getId()));
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	// need to complete and test
	public void removeCoupon(Coupon coupon) {
		Collection<Long> couponsIds;
		try {
			// check if coupon belongs to the company before remove
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);

			if (couponsIds.contains(coupon.getId())) { // the coupon exists , removal will be execute
				// removes all purchased coupons from CUSTOMER_COUPON table
				helperDAO.removeWhere("CUSTOMER_COUPON", "COUPON_ID", coupon.getId()); // need debug
				helperDAO.removeWhere("COMPANY_COUPON", "COUPON_ID", coupon.getId()); // need debug
				// removes the coupon from the COUPON table
				couponDAO.removeCoupon(coupon);
			} else {
				System.out.println("Coupon you want to remove does not belong to your company");
			}

		} catch (CouponSystemException e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

	// TEST DONE , works
	public void updqateCoupon(Coupon coupon) {
		// need check if that coupon belongs to the company
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company
			if (couponsIds.contains(coupon.getId())) {
				couponDAO.updateCoupon(coupon);
				System.out.println("Proccesing the coupon update");
			} else {
				System.out.println("This coupon doesn't belong to your Company");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}

	}

	// done and works
	public Coupon getCouponById(long id) {
		Coupon coupon = null;
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println(couponsIds);
			// checks if the coupon belongs to the company
			if (couponsIds.contains(id)) {
				coupon = couponDAO.getCoupon(id);
			} else {
				System.out.println("This coupon doesn't belong to your Company");
			}
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return coupon;

	}

	// Test done - works
	public Collection<Coupon> getAllCoupons() {
		Collection<Coupon> allCoupons = new HashSet<Coupon>();
		Collection<Long> couponsIds;
		try {
			couponsIds = helperDAO.getCouponsBelongTo("COUPON_ID", "COMPANY_COUPON", "COMPANY_ID", companyIdLogged);
			System.out.println("Coupns belong to your Company are: " + couponsIds);
			if (couponsIds.size() == 0) {
				System.out.println("There are no any coupons associated to your Compnay");
			} else {
				for (long id : couponsIds) {
					allCoupons.add(couponDAO.getCoupon(id));
				}
			}

		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return allCoupons;
	}

	// Get list of coupons sorted by : TYPE or PRICE LESS THEN or DATE BEFORE
	// 'yyyy-mm-dd'
	public Collection<Coupon> sortCouponBy(String select, String refernce) {
		Collection<Coupon> selectedCouponsBy = null;
		try {
			selectedCouponsBy = helperDAO.getCouponSelected(companyIdLogged, select, refernce);
		} catch (CouponSystemException e) {
			e.getMessage();
		}
		return selectedCouponsBy;
	}

}