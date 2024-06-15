# tls-assessment
This is the repository for a technical assessment

the project includes the gradle builder so for run you can use bootRun commands

and to test the gradle test command


sqlite database will autogenerate  under the name assessment-test.sqlite and credentials are on the config file 

  flyway:
    enabled: true
    user: admin
    password: admin
    schema: assessment-test
    locations:
      - classpath:db/dev/migration


hope everything fits with the requirement and please have fun

