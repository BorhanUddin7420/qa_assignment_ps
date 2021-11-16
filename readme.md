# Introduction

Automation Tool: Selenium Web driver, Junit, Cucumber\
Programing Language: Java\
Build Tool: Maven

# Environments Name

#### For now, This project have configured with three environments properties. You can add more as needed.

1. Local(For Local machine)
2. Dev(For dev server)
3. Qa(For test server)

# Test Casses

#### For now, This project have below test casses

1. TC01_CurrencyCalcRunner

# Build and run individual Test cases with different environment

#### Below are command to run automation script with different environment and individual test cases

    mvn test -Denv=<environmentName> -Dtest=<test case name>
    (ex: mvn test -Denv=local -Dtest=TC01_CurrencyCalcRunner)
