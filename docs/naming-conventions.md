# Naming Conventions

This project uses naming based on object responsibility, not on whether a class happens to carry data.

## Rules

- `*Request`: HTTP request objects accepted by controllers.
- `*VO`: response view objects returned by controllers.
- `*Entity`: persistence objects mapped to database tables.
- `*Param`: mapper or repository parameter objects used for SQL query/update conditions.

## Scope

- `Request` objects stop at the controller or application-service boundary.
- `Param` objects are internal and must not be reused as controller request models.
- `VO` objects are outward-facing and should use business-facing field names instead of database-oriented aliases.
- `Entity` objects should stay close to table structure and persistence concerns.

## Current examples

- `DeviceCreateRequest`: create-device API input.
- `DeviceDataReportRequest`: device report API input.
- `DeviceReportUpdateParam`: mapper parameter for updating device report metadata.
- `DeviceDataHistoryQueryParam`: mapper parameter for report history queries.
- `DeviceVO`, `DeviceDataVO`: API response objects.
- `Device`, `DeviceData`: table-mapped entities.

## Avoid

- Using `Request` for mapper parameters.
- Using `Param` for controller input.
- Reusing one object across controller, service, and mapper layers when the semantics differ.
- Naming response fields with misleading aliases such as `deviceCode` when the actual meaning is `productKey`.
