package dsa.proyecto.G4.db.orm.util;

import java.util.HashMap;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName().toLowerCase()).append(" ");
        sb.append("(");

        String[] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (String field : fields) {
            if (!field.equals("ID")) sb.append(", ").append(field);
        }
        sb.append(") VALUES (?");

        for (String field : fields) {
            if (!field.equals("ID")) sb.append(", ?");
        }
        sb.append(")");
        return sb.toString();
    }

    public static String createQuerySELECT(Class<?> theClass, String pk) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName().toLowerCase());
        sb.append(" WHERE ").append(pk).append(" = ?");
        return sb.toString();
    }

    public static String createQuerySELECTAll(Class<?> theClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName().toLowerCase());
        return sb.toString();
    }

    public static String createQueryUPDATE(Object object) {
        String[] fields = ObjectHelper.getFields(object);
        String tableName = object.getClass().getSimpleName().toLowerCase();
        StringBuilder sb = new StringBuilder("UPDATE ").append(tableName).append(" SET ");

        for (String field : fields) {
            if (!field.equals("ID")) {
                sb.append(field).append(" = ?, ");
            }
        }
        sb.delete(sb.length() - 2, sb.length()); // Eliminar la última coma
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Class<?> theClass, String pk) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(theClass.getSimpleName().toLowerCase());
        sb.append(" WHERE ").append(pk).append(" = ?");
        return sb.toString();
    }

    public static String createQuerySelectWithParams(Class<?> theClass, HashMap<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName().toLowerCase());
        sb.append(" WHERE (");

        params.forEach((k, v) -> {
            if (k.equals("password")) {
                sb.append(k).append(" = MD5(").append("?").append(") AND ");
            } else {
                sb.append(k).append(" = ").append("?").append(" AND ");
            }
        });

        sb.delete(sb.length() - 4, sb.length()); // Eliminar la última "AND"
        sb.append(")");

        return sb.toString();
    }
}
