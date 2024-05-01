open module dados {
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    exports entity;
    exports BLL;
}