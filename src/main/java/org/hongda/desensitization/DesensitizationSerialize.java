package org.hongda.desensitization;

import cn.hutool.core.text.CharSequenceUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName DesensitizationSerialize
 * @Description TODO
 * @Author liuyibo
 * @Date 2024/3/25 9:27
 **/
@AllArgsConstructor
@NoArgsConstructor
public class DesensitizationSerialize extends JsonSerializer<String> implements ContextualSerializer {
    private DesensitizationTypeEnum type;

    private Integer startIndex;

    private Integer endIndex;


    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (type){
            case MY_RULE:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case USER_ID:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case CHINESE_NAME:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case ID_CARD:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case FIXED_PHONE:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case MOBILE_PHONE:
                startIndex = 3;
                endIndex = s.length() - 3;
                jsonGenerator.writeString(CharSequenceUtil.hide(s, startIndex, endIndex));
                break;
            case ADDRESS:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case EMAIL:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case PASSWORD:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case CAR_LICENSE:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            case BANK_CARD:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,startIndex,endIndex));
                break;
            default:
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 判断数据类型是否为String类型
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 获取定义的注解
                Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
                // 为null
                if (desensitization == null) {
                    desensitization = beanProperty.getContextAnnotation(Desensitization.class);
                }
                // 不为null
                if (desensitization != null) {
                    // 创建定义的序列化类的实例并且返回，入参为注解定义的type,开始位置，结束位置。
                    return new DesensitizationSerialize(desensitization.type(), desensitization.startInclude(),
                            desensitization.endExclude());
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
