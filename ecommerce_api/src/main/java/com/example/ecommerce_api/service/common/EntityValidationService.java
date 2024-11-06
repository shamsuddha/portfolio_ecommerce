package com.example.ecommerce_api.service.common;

import com.example.ecommerce_api.api.entity.*;
import com.example.ecommerce_api.exception.UserInformException;
import com.example.ecommerce_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EntityValidationService {

  private final RoomRepository roomRepository;
  private final RoomCategoryRepository roomCategoryRepository;
  private final AppConfigInfoRepository appConfigInfoRepository;
  private final ComplainRepository complainRepository;
  private final CountryRepository countryRepository;
  private final CouponRepository couponRepository;
  private final GuestInfoRepository guestInfoRepository;
  private final HotelConfigInfoRepository hotelConfigInfoRepository;
  private final RoomCategoryRateTypeRepository roomCategoryRateTypeRepository;
  private final RoomFeatureRepository roomFeatureRepository;
  private final RoomFeatureTemplateRepository roomFeatureTemplateRepository;
  private final RoomFeatureTemplateDetailRepository roomFeatureTemplateDetailRepository;
  private final RoomRateDetailRepository roomRateDetailRepository;
  private final RoomRateTypeRepository roomRateTypeRepository;
  private final BookingRepository bookingRepository;
  private final BookingCartRepository bookingCartRepository;
  private final BookingDetailRepository bookingDetailRepository;
  private final BookingPayableRepository bookingPayableRepository;
  private final BookingReceivableRepository bookingReceivableRepository;
  private final FloorRepository floorRepository;
  private final BedRepository bedRepository;
  private final NotificationRepository notificationRepository;


  public Room validateRoom(String id) {
    Objects.requireNonNull(id);
    return roomRepository.findById(id)
        .orElseThrow(() -> new UserInformException(String
            .format("Room not found with id: [%s]", id)));
  }

  public RoomCategory validateRoomCategory(String id) {
    Objects.requireNonNull(id);
    return roomCategoryRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomCategory not found with id: [%s]", id)));
  }

  public AppConfigInfo validateAppConfigInfo(String id) {
    Objects.requireNonNull(id);
    return appConfigInfoRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("AppConfigInfo not found with id: [%s]", id)));
  }

  public Complain validateComplain(String id) {
    Objects.requireNonNull(id);
    return complainRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Complain not found with id: [%s]", id)));
  }

  public Country validateCountry(String id) {
    Objects.requireNonNull(id);
    return countryRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Country not found with id: [%s]", id)));
  }

  public Coupon validateCoupon(String id) {
    Objects.requireNonNull(id);
    return couponRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Coupon not found with id: [%s]", id)));
  }

  public GuestInfo validateGuestInfo(String id) {
    Objects.requireNonNull(id);
    return guestInfoRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("GuestInfo not found with id: [%s]", id)));
  }

  public HotelConfigInfo validateHotelConfigInfo(String id) {
    Objects.requireNonNull(id);
    return hotelConfigInfoRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("HotelConfigInfo not found with id: [%s]", id)));
  }

  public RoomCategoryRateType validateRoomCategoryRateType(String id) {
    Objects.requireNonNull(id);
    return roomCategoryRateTypeRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomCategoryRateType not found with id: [%s]", id)));
  }

  public RoomFeature validateRoomFeature(String id) {
    Objects.requireNonNull(id);
    return roomFeatureRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomFeature not found with id: [%s]", id)));
  }


  public RoomFeatureTemplate validateRoomFeatureTemplate(String id) {
    Objects.requireNonNull(id);
    return roomFeatureTemplateRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomFeatureTemplate not found with id: [%s]", id)));
  }


  public RoomFeatureTemplateDetail validateRoomFeatureTemplateDetail(String id) {
    Objects.requireNonNull(id);
    return roomFeatureTemplateDetailRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomFeatureTemplateDetail not found with id: [%s]", id)));
  }


  public RoomRateDetail validateRoomRateDetail(String id) {
    Objects.requireNonNull(id);
    return roomRateDetailRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomRateDetail not found with id: [%s]", id)));
  }

  public RoomRateType validateRoomRateType(String id) {
    Objects.requireNonNull(id);
    return roomRateTypeRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("RoomRateType not found with id: [%s]", id)));
  }

  public Booking validateBooking(String id) {
    Objects.requireNonNull(id);
    return bookingRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Booking not found with id: [%s]", id)));
  }

  public BookingCart validateBookingCart(String id) {
    Objects.requireNonNull(id);
    return bookingCartRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("BookingCart not found with id: [%s]", id)));
  }

  public BookingDetail validateBookingDetail(String id) {
    Objects.requireNonNull(id);
    return bookingDetailRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("BookingDetail not found with id: [%s]", id)));
  }

  public BookingPayable validateBookingPayable(String id) {
    Objects.requireNonNull(id);
    return bookingPayableRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("BookingPayable not found with id: [%s]", id)));
  }

  public BookingReceivable validateBookingReceivable(String id) {
    Objects.requireNonNull(id);
    return bookingReceivableRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("BookingReceivable not found with id: [%s]", id)));
  }

  public Floor validateFloor(String id) {
    Objects.requireNonNull(id);
    return floorRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Floor not found with id: [%s]", id)));
  }

  public Bed validateBed(String id) {
    Objects.requireNonNull(id);
    return bedRepository.findById(id)
            .orElseThrow(() -> new UserInformException(String
                    .format("Bed not found with id: [%s]", id)));
  }

  public Notification validateNotification(String id) {
    Objects.requireNonNull(id);
    return notificationRepository.findById(id)
      .orElseThrow(() -> new UserInformException(String
        .format("Notification not found with id: [%s]", id)));
  }

}
