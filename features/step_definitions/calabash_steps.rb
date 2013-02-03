require 'calabash-android/calabash_steps'

def check_field_or_throw(field, expected)
    if field.first['text'] != expected then
        raise "Expected: \"#{expected}\" Found: \"#{field.first['text']}\""
    end
end

Given "I am on the Player List screen" do
    check_element_exists("listview marked:'list'")
end

When "I view the details for player $player" do |player|
    check_element_exists("listview  * text:'#{player}'")
    touch("listview  * text:'#{player}'")
    sleep(1)
    check_element_exists("* marked:'root'")
end

Then "I should see the coordinates $playerX, $playerY, $playerZ" do |playerX, playerY, playerZ|
    coords = query("* marked:'location'")
    expected = "X: #{playerX} Y: #{playerY} Z: #{playerZ}"
    check_field_or_throw(coords, expected)
end

Then "I should see the name $player" do |name|
    check_field_or_throw(query("* marked:'name'"), name)
end

