package com.example.ecommerce_api.service;

import com.example.ecommerce_api.entity.Room;
import com.example.ecommerce_api.repository.RoomRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomRepository roomRepository;

  @org.springframework.transaction.annotation.Transactional
  public Room saveRoom(Room room) {
    return this.roomRepository.save(room);
  }

  @org.springframework.transaction.annotation.Transactional
  public Room updateRoom(Room room) {
    var roomDb = entityValidationService.validateRoom(room.getId());
    roomDb.setCode(room.getCode());
    roomDb.setName(room.getName());
    roomDb.setRoomCategory(room.getRoomCategory());
    roomDb.setNumberOfBed(room.getNumberOfBed());
    roomDb.setNumberOfAdultGuest(room.getNumberOfAdultGuest());
    roomDb.setNumberOfChildGuest(room.getNumberOfChildGuest());
    roomDb.setRoomSize(room.getRoomSize());
    roomDb.setDescription(room.getDescription());
    roomDb = roomRepository.save(roomDb);
    return roomDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoom(Room room) {
    var roomDb = entityValidationService.validateRoom(room.getId());
    roomDb.setEnabled(Boolean.FALSE);
    roomRepository.save(roomDb);
    return "Room deleted successfully";
  }

  public Page<Room> searchRoom(RoomSearchDto roomSearchDto) {
    Predicate predicate = makePredicate(roomSearchDto);
    Pageable pageable = PageRequest.of(roomSearchDto.getPage(), roomSearchDto.getSize());
    final QRoom qRoom = QRoom.room;
    var query = new JPAQuery<Room>(entityManager)
      .from(qRoom)
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qRoom.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<Room> searchWithAdultChildNumber(RoomSearchDto roomSearchDto) {
    Predicate predicate = makePredicate(roomSearchDto);
    Pageable pageable = PageRequest.of(roomSearchDto.getPage(), roomSearchDto.getSize());
    final QRoom qRoom = QRoom.room;
    var query = new JPAQuery<Room>(entityManager)
            .from(qRoom)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoom.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<Room> searchWithRateDetail(RoomSearchDto roomSearchDto) {
    Predicate predicate = makePredicate(roomSearchDto);
    Pageable pageable = PageRequest.of(roomSearchDto.getPage(), roomSearchDto.getSize());
    final QRoom qRoom = QRoom.room;
    final QRoomRateDetail qRoomRateDetail = QRoomRateDetail.roomRateDetail;
    var query = new JPAQuery<Room>(entityManager)
      .from(qRoom)
      .leftJoin(qRoom.roomRateDetailList, qRoomRateDetail).fetchJoin()
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qRoom.createdDate.desc());
    List<Room> roomList = query.fetch();
    /*List<Room> roomList2 = roomList.stream().map(e-> {
      e.setRoomRateDetailSerdeList(e.getRoomRateDetailList());
      return e;
    }).toList();*/
    return new PageImpl<>(roomList, pageable, query.fetchCount());
  }

  public Page<Room> searchRoomByName(RoomSearchDto roomSearchDto) {
    Predicate predicate = makePredicate(roomSearchDto);
    Pageable pageable = PageRequest.of(roomSearchDto.getPage(), roomSearchDto.getSize());
    final QRoom qRoom = QRoom.room;
    final QRoomRateDetail qRoomRateDetail = QRoomRateDetail.roomRateDetail;
    var query = new JPAQuery<Room>(entityManager)
      .from(qRoom)
      .leftJoin(qRoom.roomRateDetailList, qRoomRateDetail).fetchJoin()
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qRoom.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }


}
