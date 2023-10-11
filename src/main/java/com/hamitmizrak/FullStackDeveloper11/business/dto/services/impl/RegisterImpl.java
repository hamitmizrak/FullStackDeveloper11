package com.hamitmizrak.FullStackDeveloper11.business.dto.services.impl;

import com.hamitmizrak.FullStackDeveloper11.bean.ModelMapperBeanClass;
import com.hamitmizrak.FullStackDeveloper11.business.dto.RegisterDto;
import com.hamitmizrak.FullStackDeveloper11.business.dto.services.IRegisterServices;
import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl is Yükünü yapan yer
@Service
public class RegisterImpl implements IRegisterServices<RegisterDto, RegisterEntity> {

    // Injection
    private final IRegisterRepository iRegisterRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;

    // Model Mapper
    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(registerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBeanClass.modelMapperMethod().map(registerDto, RegisterEntity.class);
    }


    // CREATE
    // org.springframework.transaction.annotation.Transactional
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceCreate(RegisterDto registerDto) {
        if (registerDto != null) {
            RegisterEntity registerEntity = dtoToEntity(registerDto);
            iRegisterRepository.save(registerEntity);
            // Dto Set(id ve date)
            registerDto.setId(registerEntity.getId());
            registerDto.setSystemDate(registerEntity.getSystemDate());
        }
        return registerDto;
    }

    // LIST
    @Override
    public List<RegisterDto> registerServiceList() {
        // ENTITIY LIST
       Iterable<RegisterEntity> registerEntityIterable = iRegisterRepository.findAll();
       // DTO LIST
       List<RegisterDto> registerDtoList=new ArrayList<>();
       //forEach Loop
        for(RegisterEntity entity: registerEntityIterable ){
            // Entity Listesini ==> Dto Listesine çeviriyor
            registerDtoList.add(entityToDto(entity));
        }
        log.info("Register Liste Sayısı:"+registerDtoList.size());
        return registerDtoList;
    }

    // FIND BY ID
    @Override
    public RegisterDto registerServiceFindById(RegisterDto registerDto) {
        return null;
    }

    // UPDATE
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceUpdate(Long id, RegisterDto registerDto) {
        return null;
    }

    // DELETE BY ID
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceDeleteById(Long id) {
        return null;
    }
}
