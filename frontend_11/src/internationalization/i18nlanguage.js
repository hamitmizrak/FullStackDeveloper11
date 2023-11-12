import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import {useState} from "react";

i18n.use(initReactI18next).init({
    resources: {
        en:
            {
                translations: {
                    'registers': 'Registers',
                    'register': 'Register',
                    'register_list': 'Register List',
                    'register_create': 'Register Create',
                    'register_delete': 'Register Delete',
                    'register_update': 'Register Update',
                    'register_show': 'Register Show',
                    'register_nick_name':'register nick name',
                    'register_name':'register name',
                    'register_surname':'register surname',
                    'register_email':'register email',
                    'register_password':'register password',
                    'register_is_passive':'register is passive',
                    'is_read': 'Is Read ?',
                    'create_all': 'Create All',
                    'delete_all': 'Delete All',
                    'create': 'Create',
                    'delete': 'Delete',
                    'update': 'Update',
                    'cleaner': 'Cleaner',
                    'submit': 'Submit',
                    'system_date':"System Date",
                    'show': 'Show',
                    'login': 'Login',
                    'home': "Home",
                    'search': "search",
                    'all_rights_reserved':"All Rights Reserved",
                    'id':"ID",
                    'language':'Language',
                    'role': 'Role',
                    'role_name': 'Rol Name',
                    'role_list': 'Role List',
                    'role_create': 'Role Create',
                    'role_delete': 'Role Delete',
                    'role_update': 'Role Update',
                }
            },
        tr:
            {
                translations: {
                    'registers': 'Üyeler',
                    'register': 'Üye Kayıt',
                    'register_list': 'Üyeleri Liste',
                    'register_create': 'Üye Ekle',
                    'register_delete': 'Üye Sil',
                    'register_update': 'Üye Güncelle',
                    'register_show': 'Üye Göster',
                    'register_nick_name':'Takma Adı',
                    'register_name':'Adınız',
                    'register_surname':'Soyadınız',
                    'register_email':'Emailiniz',
                    'register_password':'Şifreniz',
                    'is_read': 'Okudunuz mu ?',
                    'create_all': 'Veri Ekle',
                    'delete_all': 'Bütün Verileri Sil',
                    'create': 'Ekle',
                    'delete': 'Sil',
                    'update': 'Güncelle',
                    'cleaner': 'Temizle',
                    'submit': 'Gönder',
                    'system_date':"Ekleme Tarihi",
                    'show': 'Göster',
                    'login': 'Giriş',
                    'home': "Anasayfa",
                    'search': "Arama",
                    'all_rights_reserved':"Bütün Haklar Saklıdır",
                    'id':"ID",
                    'language':'Diller',
                    'role': 'Rol Atama',
                    'role_name': 'Rol Adı',
                    'role_list': 'Rol Listesi',
                    'role_create': 'Rol Oluştur',
                    'role_delete': 'Rol Sil',
                    'role_update': 'Rol Güncelle',
                }
            }
    },
    fallbackLng: 'tr',    //fallbackLng: 'en', fall back function    
    ns: ['translations'], //kelimeleri nerede alsın
    defaultNS: 'translations',
    keySeparator: false,
    interpolation: {escapeValue: false, formatSeparator: ','},
    react: {
        wait: true
    }
});
export default i18n;