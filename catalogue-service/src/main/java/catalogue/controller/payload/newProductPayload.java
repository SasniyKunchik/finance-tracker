package manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record newProductPayload(
        @NotNull(message = "{catalogue.products.create.errors.title_is_invalid}")
        @Size(min = 3, max = 50, message = "{catalogue.products.create.errors.title_is_invalid}")

        String title,
        @Size(max = 1000,  message = "{catalogue.products.create.errors.details_is_invalid}")
        String details) {


}
