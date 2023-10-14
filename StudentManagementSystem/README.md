# Student Management System API Documentation

This documentation provides details about the RESTful APIs for managing students in the Student Management System.

## Base URL

The base URL for all API endpoints is:


````
http://your-api-base-url/api/v1
````


## Endpoints Summary

| Endpoint                                     | Method | Description                                         | Parameters                                   | Request Body      | Response                                    | Error Handling                                           |
|----------------------------------------------|--------|-----------------------------------------------------|----------------------------------------------|-------------------|---------------------------------------------|-----------------------------------------------------------|
| `/students`                                  | GET    | Get all students                                   | -                                            | -                 | `200 OK`: List of Student objects           | -                                                         |
| `/students/year_of_registration?year=year`              | GET    | Get students by year of registration              | `year` (optional)                            | -                 | `200 OK`: List of Student objects           | `400 Bad Request`: ErrorResponse if 'year' is missing or invalid |
| `/students/{id}`                             | GET    | Get student by ID                                  | `id`: Student ID                            | -                 | `200 OK`: Student object                   | `404 Not Found`: ErrorResponse if student with ID doesn't exist |
| `/students/{id}`                             | PUT    | Update student                                    | `id`: Student ID                            | Student object    | `200 OK`: Updated Student object          | `404 Not Found`: ErrorResponse if student with ID doesn't exist |
| `/students`                                  | POST   | Create student                                    | -                                            | StudentDto object | `200 OK`: Created Student object          | `400 Bad Request`: Map of field errors if validation errors occur |
| `/students/{id}`                             | DELETE | Delete student                                    | `id`: Student ID                            | -                 | `200 OK`: Map indicating successful deletion | `404 Not Found`: ErrorResponse if student with ID doesn't exist |

## Error Response Structure (ErrorResponse DTO)

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "The 'year' parameter is required.",
  "path": "/api/v1/students/year_of_registration?year=null"
}

```


