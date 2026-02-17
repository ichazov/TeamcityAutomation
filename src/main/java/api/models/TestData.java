package api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData extends BaseModel {
    private Project project;
    private User user;
    private BuildType buildType;
    private Roles roles;
}
