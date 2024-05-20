require 'date'

module AddUsersHelper
  def validate_login_name(login_name, action_type)
    error = ''
    # Validate login_name
    if login_name.present?
      if check_length(login_name, Constant::MaxMin::MAXLENGHT_LOGIN_NAME, Constant::MaxMin::MINLENGHT_LOGIN_NAME)
        if check_format(login_name, Constant::Regex::REGEX_LOGIN_NAME)
          error = Constant::Error::ER003_loginName if check_exist_email_and_login_name(login_name, action_type)
        else
          error = Constant::Error::ER019
        end
      else
        error = Constant::Error::ER007_loginName
      end
    else
      error = Constant::Error::ER001_loginName
    end
    error
  end

  def check_length(value, max_length, min_length)
    value && value.length >= min_length && value.length <= max_length
  end

  def check_format(value, regex)
    value && value =~ regex
  end

  def check_exist_email_and_login_name(value, action_type)
    return false if action_type == 'edit'
    user = value.include?('@') ? TblUser.where(email: value).first : TblUser.where(login_name: value).first
    user.present?
  end

  # Validates the entered group
  def validate_group(group)
    error = ''
    # Validate group
    if group != 0
      error = Constant::Error::ER004_group if check_not_exist_group(group)
    else
      error = Constant::Error::ER002_group
    end
    error
  end

  def check_not_exist_group(group)
    MstGroup.where(group_id: group).first.nil?
  end

  # Validates the entered full name
  def validate_full_name(fullName)
    error = ''
    # Validate fullName
    if fullName.present?
      error = Constant::Error::ER006_fullName unless check_length(fullName, Constant::MaxMin::MAXLENGHT_FULL_NAME, 1)
    else
      error = Constant::Error::ER001_fullName
    end
    error
  end

  def validate_full_name_kana(full_name_kana)
    error = ""
    # validate fullNameKana
    if full_name_kana.present?
      if check_format(full_name_kana, Constant::Regex::REGEX_FULL_NAME_KANA)
        error = Constant::Error::ER006_fullNameKana unless check_length(full_name_kana, Constant::MaxMin::MAXLENGHT_FULL_NAME_KANA, 1)
      else
        error = Constant::Error::ER009_fullNameKana
      end
    end
    error
  end

  def validate_birth_day(day, month, year)
    error = ""
    # validate birthday
    error = Constant::Error::ER011_birthDay unless check_valid_day(day, month, year)
    error
  end

  def check_valid_day(day, month, year)
    check = false
    if year % 4 == 0
      if month == 2 && day <= 29
        check = true
      elsif (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31
        check = true
      elsif (month == 4 || month == 6 || month == 9 || month == 11) && day <= 30
        check = true
      end
    else
      if month == 2 && day <= 28
        check = true
      elsif (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31
        check = true
      elsif (month == 4 || month == 6 || month == 9 || month == 11) && day <= 30
        check = true
      end
    end
    check
  end

  # validate email
  def validate_email(email, action_type)
    error = ""
    if email.present?
      if check_length(email, Constant::MaxMin::MAXLENGHT_EMAIL, 1)
        if check_format(email, Constant::Regex::REGEX_EMAIL)
          error = Constant::Error::ER003_email if check_exist_email_and_login_name(email, action_type)
        else
          error = Constant::Error::ER005_email
        end
      else
        error = Constant::Error::ER006_email
      end
    else
      error = Constant::Error::ER001_email
    end
    error
  end

  # validate tel
  def validate_tel(tel)
    error = ""
    if tel.present?
      if check_length(tel, Constant::MaxMin::MAXLENGHT_TEL, 1)
        error = Constant::Error::ER005_tel unless check_format(tel, Constant::Regex::REGEX_TEL)
      else
        error = Constant::Error::ER006_tel
      end
    else
      error = Constant::Error::ER001_tel
    end
    error
  end

  # validate pass and pass_confirm
  def validate_pass_and_pass_confirm(pass, pass_confirm)
    error = ""
    if pass.present?
      if check_format(pass, Constant::Regex::REGEX_PASS)
        if check_length(pass, Constant::MaxMin::MAXLENGHT_PASS, Constant::MaxMin::MINLENGHT_PASS)
          error = Constant::Error::ER017 unless pass == pass_confirm
        else
          error = Constant::Error::ER007_password
        end
      else
        error = Constant::Error::ER008_password
      end
    else
      error = Constant::Error::ER001_password
    end
    error
  end

  # validate name_level
  def validate_name_level(code_level)
    error = ""
    error = Constant::Error::ER004_japanLevel if code_level != '0' && check_not_exist_japan(code_level)
    error
  end

  def check_not_exist_japan(code_level)
    MstJapan.where(code_level: code_level).first.nil?
  end

  # validate startDate
  def validate_start_day(day_start, month_start, year_start, code_level)
    error = ""
    error = Constant::Error::ER011_startDate if code_level != "0" && !check_valid_day(day_start, month_start, year_start)
    error
  end

  # validate EndDate
  def validate_end_day(day_end, month_end, year_end, code_level)
    error = ""
    error = Constant::Error::ER011_endDate if code_level != "0" && !check_valid_day(day_end, month_end, year_end)
    error
  end

  # validate total
  def validate_total(total, code_level)
    error = ""
    if code_level.present?
      if check_format(total.to_s, Constant::Regex::REGEX_TOTAL)
        if check_length(total.to_s, Constant::MaxMin::MAXLENGHT_TOTAL, 1)
          error = Constant::Error::ER021_total if total.to_i < 90 && total.to_i > 180
        else
          error = Constant::Error::ER006_total
        end
      else
        error = Constant::Error::ER018_total
      end
    else
      error = Constant::Error::ER001_total
    end
    error
  end
end
