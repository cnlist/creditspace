package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import us.cnlist.objects.contacts.ContactType;

import java.io.Serializable;

public class ContactTypeDesc implements Serializable {

    private ContactType contactType;
    private String description;

    public ContactTypeDesc(ContactType type) {

        this.contactType = type;
        switch (type) {
            case EMAIL:
                this.description = "Электронная почта";
                break;
            case VIBER:
                this.description = "Мессенджер Viber";
                break;
            case CELLPHONE:
                this.description = "Мобильный телефон";
                break;
            case VK_PROFILE:
                this.description = "Профиль ВКонтакте";
                break;
            case FACEBOOK_PROFILE:
                this.description = "Профиль Facebook";
                break;
            case TELEGRAM:
                this.description = "Мессенджер Telegram";
                break;
            case WHATSAPP:
                this.description = "Мессенджер Whatsapp";
                break;
        }
    }

    public ContactTypeDesc(){}

    public String getDescription() {
        return description;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
