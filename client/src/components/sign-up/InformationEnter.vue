<script lang="ts">
import { useQuasar } from 'quasar'
import { useAuthStore } from 'src/stores/auth-store'
import { defineComponent, ref } from 'vue'
import { Form as ValidationForm, useForm, useField } from 'vee-validate'
import * as yup from 'yup'
import { BIRTHDAT_REGEX, EMAIL_REGEX, PASSWORD_REGEX } from 'src/common/regexs'
import { GenderType } from 'src/common/models'
import { FindMemberUserByUserId } from 'src/graphql/dto/member-user-input'
import dayjs from 'dayjs'

export default defineComponent({
  components: {
    ValidationForm,
  },
  setup() {
    const authStore = useAuthStore()
    const { signUpInputData, checkedInUseCaseFromUserId } = authStore
    const isPwd = ref<boolean>(true)
    const phoneNumber = signUpInputData.get('phone-number')

    const validationSchema = yup.object({
      userId: yup
        .string()
        .defined()
        .matches(EMAIL_REGEX, '이메일 형식이 아닙니다.')
        .required('아이디 값은 필수 값입니다.'),
      password: yup
        .string()
        .defined()
        .matches(PASSWORD_REGEX, '10~18자의 영문 숫자 포함을 권장합니다.')
        .min(10, '10~18자의 영문, 숫자 포함을 권장합니다.')
        .max(18, '10~18자의 영문, 숫자 포함을 권장합니다.')
        .required('비밀번호는 필수 정보입니다.'),
      passwordConfirm: yup
        .string()
        .defined()
        .oneOf([yup.ref<string>('password')], '비밀번호가 일치하지 않습니다.'),
      gender: yup
        .string()
        .defined()
        .oneOf(
          <GenderType[]>['FEMALE', 'MALE', 'OTHER'],
          '성별은 남자, 여자, 기타만 가능합니다.'
        )
        .required('성별은 필수 정보입니다.'),
      name: yup.string(),
      birthday: yup
        .string()
        .matches(BIRTHDAT_REGEX, '생년월일을 확인해주세요.'),
    })

    const { errors, meta, setFieldError } = useForm({
      validationSchema,
    })

    const { value: userId } = useField<string>('userId')
    const { value: password } = useField<string>('password', {
      validateOnInput: true,
    })
    const { value: passwordConfirm } = useField<string>('passwordConfirm', {
      validateOnInput: true,
    })
    const { value: name } = useField<string>('name')
    const { value: gender } = useField<GenderType>('gender', {
      validateOnInput: true,
    })
    const { value: birthday } = useField<string>('birthday')

    const goNextStep = (): void => {
      checkedUserId()
    }

    const checkedUserId = async (): Promise<void> => {
      const dto: FindMemberUserByUserId = {
        userId: userId.value ?? '',
      }
      const valid: boolean = await checkedInUseCaseFromUserId(dto)
      if (!valid) return setFieldError('userId', '이미 사용 중인 아이디입니다.')
      else return
    }

    const checkedBirthDay = (): void => {
      const validBirthDay = birthday.value ?? ''

      if (validBirthDay.length < 8) return
      const year = parseInt(validBirthDay.substring(0, 4))
      const month = parseInt(validBirthDay.substring(4, 6))
      const day = parseInt(validBirthDay.substring(6))
      console.log(year, month, day)
      const lastDayOfMonth = dayjs(`${year}-${month}`).endOf('month').date()

      if (day > lastDayOfMonth)
        return setFieldError('birthday', '유효한 생년월일이 아닙니다.')
    }

    const state = {
      isPwd,
      errors,
      meta,
      userId,
      name,
      password,
      passwordConfirm,
      phoneNumber,
      gender,
      birthday,
    }
    const action = { goNextStep, checkedUserId, checkedBirthDay }
    return {
      ...state,
      ...action,
    }
  },
})
</script>

<template>
  <validation-form @submit="goNextStep">
    <q-card-section>
      <q-input
        stack-label
        clear-icon="close"
        clearable
        color="positive"
        v-model="userId"
        placeholder="이메일 주소를 입력해주세요."
        type="text"
        no-error-icon
        :error="!!errors.userId"
        label="아이디"
        class="essential-input"
        @blur="checkedUserId"
      >
        <template #error>
          {{ errors.userId }}
        </template>
      </q-input>
      <q-input
        stack-label
        clear-icon="close"
        clearable
        color="positive"
        v-model="password"
        :type="isPwd ? 'password' : 'text'"
        no-error-icon
        :error="!!errors.password"
        placeholder="비밀번호를 입력해주세요."
        autocomplete="off"
        label="비밀번호"
        class="essential-input"
      >
        <template v-slot:append
          ><q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
        <template #error>
          {{ errors.password }}
        </template>
      </q-input>
      <q-input
        stack-label
        clear-icon="close"
        clearable
        color="positive"
        v-model="passwordConfirm"
        type="password"
        no-error-icon
        :error="!!errors.passwordConfirm"
        placeholder="비밀번호를 한번 더 입력해주세요."
        autocomplete="off"
        label="비밀번호 확인"
        class="essential-input"
      >
        <template #error>
          {{ errors.passwordConfirm }}
        </template>
      </q-input>
      <q-input
        stack-label
        color="positive"
        v-model="phoneNumber"
        type="text"
        label="휴대폰 번호"
        readonly
        disable
      />
    </q-card-section>
    <q-card-section class="text-black">
      <div class="essential-div">성별</div>
      <div class="q-gutter-sm">
        <q-radio
          v-model="gender"
          checked-icon="task_alt"
          unchecked-icon="panorama_fish_eye"
          color="positive"
          val="MALE"
          label="남자"
        />
        <q-radio
          v-model="gender"
          checked-icon="task_alt"
          unchecked-icon="panorama_fish_eye"
          color="positive"
          val="FEMALE"
          label="여자"
        />
        <q-radio
          v-model="gender"
          checked-icon="task_alt"
          unchecked-icon="panorama_fish_eye"
          color="positive"
          val="OTHER"
          label="기타"
        />

        <div class="text-red">{{ errors.gender }}</div>
      </div>

      <q-input
        stack-label
        color="positive"
        v-model="name"
        type="text"
        label="이름"
        placeholder="이름을 입력해주세요."
      />
      <q-input
        stack-label
        color="positive"
        v-model="birthday"
        type="text"
        label="생년월일"
        :error="!!errors.birthday"
        no-error-icon
        placeholder="8자리를 입력해주세요."
        @blur="checkedBirthDay"
        mask="########"
      >
        <template #error>
          {{ errors.birthday }}
        </template>
      </q-input>
    </q-card-section>

    <q-card-actions>
      <q-btn
        flat
        label="다음으로"
        :class="meta.valid ? 'bg-teal' : 'bg-grey'"
        type="submit"
      />
    </q-card-actions>
  </validation-form>
</template>
