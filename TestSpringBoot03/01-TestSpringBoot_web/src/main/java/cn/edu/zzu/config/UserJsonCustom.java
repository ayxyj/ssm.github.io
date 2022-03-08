package cn.edu.zzu.config;

/**
 * 自行进行序列化user实体的属性，控制更加灵活
 */
//@JsonComponent
public class UserJsonCustom {
//    public static class Serializer extends JsonObjectSerializer<User> {
//        /**
//         * 1、一次查不出完整的数据返回给客户端，需要根据需求去做一些个性化调整
//         * 2、根据不同的权限给他返回不同的序列化数据
//         */
//
//        @Override
//        protected void serializeObject(User user, JsonGenerator jgen, SerializerProvider provider) throws IOException {
//            jgen.writeObjectField("id", user.getId());
//            jgen.writeObjectField("username", "自行序列化");
//            /**
//             //手动添加kv
//             jgen.writeFieldName();
//             jgen.writeObject();
//             */
//
//        }
//    }
//
//    public static class DeSerializer extends JsonObjectDeserializer {
//
//        @Override
//        protected Object deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
//           User user = new User();
//           user.setId(tree.findValue("id").asInt());
//            return user;
//        }
//    }
}
