package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileResponseLombokModel {


    // Когда RestAssured получает JSON-ответ, он видит структуру:
    // {
    //    "data": { ... },  // Этот объект будет преобразован в экземпляр класса Data
    //    "support": { ... }
    // }
    // Поле data в Java-классе хранит объект типа Data, который содержит: id, email, first_name, ...

    // Поля data и support в классе должны точно совпадать с ключами JSON, иначе десериализация
    // (преобразование JSON → Java-объект) не сработает.


    private Data data; // data – поле, соответствующее вложенному JSON-объекту "data" из API-ответа.
    private Support support; // support – поле, соответствующее вложенному JSON-объекту "support".
    // Оба поля являются /объектами других классов (Data и Support), которые описывают структуру вложенных данных.

    // поскольку JSON содержит вложенные объекты, их тоже нужно описать как отдельные классы.

    // @lombok.Data – аналогично @Data, но здесь указан полный путь (lombok.Data),
    // чтобы избежать конфликтов, если в проекте есть другие аннотации @Data
    // static class Data – вложенный статический класс, который описывает структуру объекта "data" из JSON

    @lombok.Data
    public static class Data {
        private String id;
        private String email;

        // В JSON поле называется "first_name" (snake_case), но в Java принято использовать camelCase (firstName).
        // Аннотация @JsonProperty из Jackson говорит: "Сопоставь JSON-поле first_name с Java-полем firstName"
        @JsonProperty("first_name")  // Если JSON использует snake_case, а Java — camelCase
        private String firstName;
        @JsonProperty("last_name")   // Аннотация Jackson для маппинга полей
        private String lastName;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}
