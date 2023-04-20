import math


class Clock:
    def __init__(self, hours: int, minutes: int):
        if hours < 0 or hours > 24 or minutes < 0 or minutes > 59:
            self.hours = 0
            self.minutes = 0
        else:
            self.hours = hours
            self.minutes = minutes

    def set_time(self, hours, minutes) -> None:
        if hours < 0 or hours > 24 or minutes < 0 or minutes > 59:
            self.hours = 0
            self.minutes = 0
        else:
            self.hours = hours
            self.minutes = minutes

    def add_minutes(self, minutes) -> None:
        self.minutes = (minutes + self.minutes) % 60
        if minutes < 0:
            self.hours = (self.hours + minutes // 60) % 24
        else:
            self.hours = (self.hours + math.ceil(minutes / 60)) % 24

    def __str__(self) -> str:
        string = ""

        if self.hours < 10:
            string += "0"

        string += str(self.hours) + ":"

        if self.minutes < 10:
            string += "0"

        string += str(self.minutes)

        return string

    def is_even_hour(self) -> bool:
        return self.hours % 2 == 0


c = Clock(10, 30)
c.set_time(24, 60)
print(c)
