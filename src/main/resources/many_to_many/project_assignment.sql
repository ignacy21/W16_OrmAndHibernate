CREATE TABLE project_assignment
(
    project_assignment_id   SERIAL      NOT NULL,
    employee_id             INT         NOT NULL,
    project_id              INT         NOT NULL,
    PRIMARY KEY (project_assignment_id),
    CONSTRAINT fk_project_assignment_employee
        FOREIGN KEY (employee_id)
            REFERENCES employee (employee_id),
    CONSTRAINT fk_project_assignment_project
        FOREIGN KEY (project_id)
            REFERENCES project (project_id)
);