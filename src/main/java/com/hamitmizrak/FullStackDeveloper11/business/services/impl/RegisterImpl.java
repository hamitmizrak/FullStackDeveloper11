package com.hamitmizrak.FullStackDeveloper11.business.services.impl;

import com.hamitmizrak.FullStackDeveloper11.bean.ModelMapperBeanClass;
import com.hamitmizrak.FullStackDeveloper11.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.FullStackDeveloper11.business.dto.RegisterDto;
import com.hamitmizrak.FullStackDeveloper11.business.services.IRegisterServices;
import com.hamitmizrak.FullStackDeveloper11.business.services.ITokenServices;
import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import com.hamitmizrak.FullStackDeveloper11.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRegisterRepository;
import com.hamitmizrak.FullStackDeveloper11.data.repository.ITokenRepository;
import com.hamitmizrak.FullStackDeveloper11.exception.HamitMizrakException;
import com.hamitmizrak.FullStackDeveloper11.exception.Resource404NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICE
// Asıl is Yükünü yapan yer
@Service
public class RegisterImpl implements IRegisterServices<RegisterDto, RegisterEntity> {

    // INJECTION
    private final IRegisterRepository iRegisterRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    @Autowired
    private JavaMailSender mailSender; // Mail oluşturma
    @Value("${spring.mail.username}")
    private String serverMailAddress;
    private final ITokenServices tokenServices; // Email Token confirmation
    private final ITokenRepository iTokenRepository; // Token oluşturma

    ////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(registerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBeanClass.modelMapperMethod().map(registerDto, RegisterEntity.class);
    }

    /////////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public List<RegisterDto> registerServiceSpeedData(Long key) {
        List<RegisterDto> registerDtoList=new ArrayList<>();
        if (key != null) {
            for (int i = 1; i <=key ; i++) {
                RegisterDto registerDto=RegisterDto
                        .builder()
                        .registerNickName("nick name "+i)
                        .registerName("name "+i)
                        .registerSurname("surname "+i)
                        //.registerPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(UUID.randomUUID().toString()))
                        .registerPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode("root"))
                        .registerEmail("hamitmizrak"+UUID.randomUUID().toString()+"@gmail.com")
                        .isAccountNonLocked(false) //mail ile aktifleştirelim
                        .isEnabled(true)
                        .isAccountNonExpired(true)
                        .isCredentialsNonExpired(true)
                        .build();
                // Model Mapper
                RegisterEntity registerEntity = dtoToEntity(registerDto);
                // Save
                iRegisterRepository.save(registerEntity);
                // Set
                registerDto.setId(registerEntity.getRegisterId());
                registerDto.setSystemDate(registerEntity.getSystemDate());
                registerDtoList.add(registerDto);
            }
        }
        registerDtoList.stream().forEach((temp)->
                System.out.println(temp)
        );
         /*
         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if(authentication!=null && authentication.isAuthenticated()){
                System.out.println(authentication.getName());
                System.out.println(authentication.getPrincipal());
            }
            */
        return registerDtoList;
    }

    // DELETE ALL
    @Override
    public String registerServiceDeleteAll() {
        iRegisterRepository.deleteAll();
        System.out.println(iRegisterRepository.findAll().toString());
        return iRegisterRepository.findAll().toString();
    }

    /////////////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    // org.springframework.transaction.annotation.Transactional
    // Transaction: Create, Delete, Update
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceCreate(RegisterDto registerDto) {
        if (registerDto != null) {
            RegisterEntity registerEntity = dtoToEntity(registerDto);
            // Password Encoder Bean
            //passwordEncoderBeanClass.passwordEncoderMethod().encode(registerDto.getRegisterPassword());
            // PasswordEncoder
            registerEntity.setRegisterPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(registerDto.getRegisterPassword()));
            iRegisterRepository.save(registerEntity);
            // Dto Set(id ve date)
            registerDto.setId(registerEntity.getRegisterId());
            registerDto.setSystemDate(registerEntity.getSystemDate());

            //MAIL GONDER VE TOKEN OLUŞTUR
            // TOKEN OLUŞTUR
            TokenConfirmationEntity tokenConfirmationEntity = new TokenConfirmationEntity(registerEntity);
            String token = tokenServices.createToken(tokenConfirmationEntity);
            SimpleMailMessage message = new SimpleMailMessage();
            System.out.println("APP 44 ==> "+serverMailAddress);
            message.setFrom(this.serverMailAddress);
            message.setTo(registerDto.getRegisterEmail());
            message.setSentDate(new Date(System.currentTimeMillis()));
            message.setSubject("Üyeliğiniz Aktif olmasına son bir adım kaldı");
            //message.setBcc(this.serverMailAddress);
            //message.setCc(this.serverMailAddress);
            String mailContent = "Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız. " + "http://localhost:4444/register/api/v1.0.0/confirm?token=" + token;
            message.setText(mailContent);
            mailSender.send(message);

            return registerDto;
        }
        return null;
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
    public RegisterDto registerServiceFindById(Long id) {
        // 1.YOL
        /*
        Optional<RegisterEntity> findRegisterEntityWay1=iRegisterRepository.findById(id);
        RegisterDto registerDtoWay1=entityToDto(findRegisterEntityWay1.get());
        if(findRegisterEntityWay1.isPresent()){
            return registerDtoWay1;
        }
        */

        // 2.YOL
        RegisterEntity findRegisterEntityWay2=null;
        if(id!=null){
            findRegisterEntityWay2=iRegisterRepository.findById(id).orElseThrow(
                    ()->new Resource404NotFoundException(id+ " nolu id yoktur")
            );
        }else if(id==null){
            throw new HamitMizrakException("id null olarak geldi");
        }
        return entityToDto(findRegisterEntityWay2);
    }

    // UPDATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceUpdate(Long id, RegisterDto registerDto) {
        // Öncelikle ilgili Register kaydını bulmalısın.
        RegisterDto registerFindDto= registerServiceFindById(id);
        //Entity Instance
        RegisterEntity registerEntity = null;
        if(registerFindDto!=null){
            registerEntity=dtoToEntity(registerDto);
            registerEntity.setRegisterId(registerDto.getId());
            registerEntity.setRegisterNickName(registerDto.getRegisterNickName());
            registerEntity.setRegisterName(registerDto.getRegisterName());
            registerEntity.setRegisterSurname(registerDto.getRegisterSurname());
            registerEntity.setRegisterEmail(registerDto.getRegisterEmail());
            registerEntity.setRegisterPassword(registerDto.getRegisterPassword());
            iRegisterRepository.save(registerEntity);
        }
        return entityToDto(registerEntity);
    }

    // DELETE BY ID
    // Transaction: Create, Delete, Update
    @Override
    @Transactional // create , delete, update
    public RegisterDto registerServiceDeleteById(Long id) {
        // Öncelikle ilgili Register kaydını bulmalısın.
        RegisterDto registerFindDto= registerServiceFindById(id);
        if(registerFindDto!=null){
            iRegisterRepository.deleteById(id);
        }
        return registerFindDto;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // EMAIL TOKEN CONFIRMATION
    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity) {
        // @OneToOne(1-1) ilişkideki veriyi almak
        // TokenConfirmationEntity'den UserEntity almak
        final RegisterEntity userEntity = tokenConfirmationEntity.getUserEntity();
        // üyeliği aktif et
        // Embeddable eklediğim
        userEntity.getUserDetailsEmbeddable().setIsAccountNonLocked(Boolean.TRUE);
        iRegisterRepository.save(userEntity);
        // Mail onaylanması sonrasında database Tokenı sil
        tokenServices.deleteToken(tokenConfirmationEntity.getId());
    }

    // TOKEN FIND
    @Override
    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token) {
        return iTokenRepository.findTokenConfirmationEntityByToken(token);
    }

} //end class
