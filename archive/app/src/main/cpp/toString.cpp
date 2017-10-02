//
// Created by procon-kyougi on 2017/09/21.
//

#include<string>
#include<iostream>
#include<cstdio>
#include<limits>
#include"toString.h"

namespace matatabi{

    std::string to_string(int val)
    {
        char buffer[std::numeric_limits<int>::digits10 + 2]; // '-' + NULL
        std::sprintf(buffer, "%d", val);
        return buffer;
    }

    std::string to_string(unsigned int val)
    {
        char buffer[std::numeric_limits<unsigned int>::digits10 + 1];
        std::sprintf(buffer, "%u", val);
        return buffer;
    }

    std::string to_string(long val)
    {
        char buffer[std::numeric_limits<long>::digits10 + 2]; // '-' + NULL
        std::sprintf(buffer, "%ld", val);
        return buffer;
    }

    std::string to_string(unsigned long val)
    {
        char buffer[std::numeric_limits<unsigned long>::digits10 + 1];
        std::sprintf(buffer, "%lu", val);
        return buffer;
    }

    std::string to_string(long long int val)
    {
        char buffer[std::numeric_limits<long long int>::digits10 + 2]; // '-' + NULL
        std::sprintf(buffer, "%lld", val);
        return buffer;
    }

    std::string to_string(unsigned long long int val)
    {
        char buffer[std::numeric_limits<unsigned long long int>::digits10 + 1];
        std::sprintf(buffer, "%llu", val);
        return buffer;
    }

    std::string to_string(float val)
    {
        char buff[std::numeric_limits<float>::max_exponent10
                  + 6   // fixed precision (printf's default)
                  + 3]; // '-' + '.' + NULL
        std::sprintf(buff, "%f", val);
        return buff;
    }

    std::string to_string(double val)
    {
        char buff[std::numeric_limits<double>::max_exponent10
                  + 6   // fixed precision (printf's default)
                  + 3]; // '-' + '.' + NULL
        std::sprintf(buff, "%f", val);
        return buff;
    }

    std::string to_string(long double val)
    {
        char buff[std::numeric_limits<long double>::max_exponent10
                                                  + 6   // fixed precision (printf's default)
                                                  + 3]; // '-' + '.' + NULL
        std::sprintf(buff, "%Lf", val);
        return buff;
    }
}